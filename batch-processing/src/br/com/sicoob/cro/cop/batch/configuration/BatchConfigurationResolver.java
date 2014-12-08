/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sicoob.cro.cop.batch.configuration;

import br.com.sicoob.cro.cop.batch.configuration.annotation.BatchConfiguration;
import br.com.sicoob.cro.cop.batch.configuration.annotation.Jobs;
import br.com.sicoob.cro.cop.batch.job.Job;
import br.com.sicoob.cro.cop.util.MandatoryFieldException;
import br.com.sicoob.cro.cop.util.NoConfigurationClassException;
import br.com.sicoob.cro.cop.util.NoJobsFoundException;
import br.com.sicoob.cro.cop.util.ObjectDomainsUtil;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Resolve a obtencao dos dados de uma classe de configuracao de batch.
 *
 * @author Rogerio Alves Rodrigues
 */
public class BatchConfigurationResolver {

    // log
    private static final Logger LOG = Logger.getLogger(BatchConfigurationResolver.class.getName());

    // objeto a ser resolvido
    private final Object configuration;

    /**
     * Constroi um resolvedor.
     *
     * @param configuration Classe de configuracao de batch.
     */
    public BatchConfigurationResolver(Object configuration) {
        this.configuration = configuration;
    }

    /**
     * Obtem a lista de jobs a serem executados de acordo com a classe de
     * configuracao.
     *
     * @return Lista de Jobs.
     * @throws IllegalAccessException Erro.
     * @throws IllegalArgumentException Erro.
     * @throws InvocationTargetException Erro.
     * @throws NoConfigurationClassException Erro.
     * @throws NoJobsFoundException Erro.
     * @throws br.com.sicoob.cro.cop.util.MandatoryFieldException Erro.
     */
    public List<Job> getJobs() throws IllegalAccessException, IllegalArgumentException,
            InvocationTargetException, NoConfigurationClassException, NoJobsFoundException, MandatoryFieldException {

        LOG.log(Level.INFO, "Verifica se existe a anotacao [@BatchConfiguration] para classe ["
                .concat(configuration.getClass().getName()).concat("]"));

        List<Job> jobs = new ArrayList<>();
        // verifica se contem a annotation @BatchConfiguration
        if (configuration.getClass().isAnnotationPresent(BatchConfiguration.class)) {
            // encontra o metodo que retorna os jobs
            Method[] methods = configuration.getClass().getDeclaredMethods();
            for (Method method : methods) {
                if (method.isAnnotationPresent(Jobs.class)) {
                    LOG.log(Level.INFO, "Obtendo os jobs do metodo que contem a anotacao [@Jobs] o metodo ["
                            .concat(method.getName()).concat("]"));
                    method.setAccessible(Boolean.TRUE);
                    jobs.addAll((List<Job>) method.invoke(configuration, new Object[0]));
                }
            }
        } else {
            throw new NoConfigurationClassException("Nenhuma @BatchConfiguration anotacao encontrada");
        }

        if (jobs.isEmpty()) {
            throw new NoJobsFoundException("Nenhum Job encontrado, verificar lista nula ou a falta da anotacao @Jobs");
        }

        return (List<Job>) ObjectDomainsUtil.nullSafe(jobs, Collections.emptyList());
    }
}
