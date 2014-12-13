/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sicoob.cro.cop.batch.configuration;

import br.com.sicoob.cro.cop.batch.configuration.annotation.BatchConfiguration;
import br.com.sicoob.cro.cop.batch.configuration.annotation.Jobs;
import br.com.sicoob.cro.cop.batch.job.Job;
import br.com.sicoob.cro.cop.util.BatchPropertiesUtil;
import br.com.sicoob.cro.cop.util.NoConfigurationClassException;
import br.com.sicoob.cro.cop.util.NoJobsFoundException;
import br.com.sicoob.cro.cop.util.ObjectDomainsUtil;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.commons.beanutils.MethodUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Resolve a obtencao dos dados de uma classe de configuracao de batch.
 *
 * @author Rogerio Alves Rodrigues
 */
public class BatchConfigurations {

    // log
    private static final Log LOG = LogFactory.getLog(BatchConfigurations.class.getName());

    // objeto a ser resolvido
    private final Object configurationObject;

    /**
     * Constroi um resolvedor.
     *
     * @param configurationObject Classe de configuracao de batch.
     */
    public BatchConfigurations(Object configurationObject) {
        this.configurationObject = configurationObject;
    }

    /**
     * Obtem a lista de jobs a serem executados de acordo com a classe de
     * configuracao.
     *
     * @return Lista de Jobs.
     * @throws Exception Erro.
     */
    public List<Job> getJobs() throws Exception {
        LOG.info(BatchPropertiesUtil.getInstance().getMessage("batch.configurations.verificacao",
                this.configurationObject.getClass().getName()));
        List<Job> jobs = new ArrayList();
        if (this.configurationObject.getClass().isAnnotationPresent(BatchConfiguration.class)) {
            Method[] methods = this.configurationObject.getClass().getDeclaredMethods();
            for (Method method : methods) {
                if (method.isAnnotationPresent(Jobs.class)) {
                    LOG.info("Obtendo os jobs do metodo que contem a anotacao [@Jobs] o metodo ["
                            .concat(method.getName()).concat("]"));
                    method.setAccessible(Boolean.TRUE);
                    jobs.addAll((List<Job>) MethodUtils.invokeExactMethod(this.configurationObject, method.getName(), new Object[0]));
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
