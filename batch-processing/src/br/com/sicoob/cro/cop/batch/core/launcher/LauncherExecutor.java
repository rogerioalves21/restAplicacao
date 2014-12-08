/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sicoob.cro.cop.batch.core.launcher;

import br.com.sicoob.cro.cop.batch.configuration.BatchConfigurationResolver;
import br.com.sicoob.cro.cop.batch.configuration.JobFactoryInjector;
import br.com.sicoob.cro.cop.batch.configuration.StepFactoryInjector;
import br.com.sicoob.cro.cop.batch.configuration.annotation.Inject;
import br.com.sicoob.cro.cop.batch.core.CoreExecution;
import br.com.sicoob.cro.cop.batch.core.Execution;
import br.com.sicoob.cro.cop.batch.core.DataExecution;
import br.com.sicoob.cro.cop.batch.core.Result;
import br.com.sicoob.cro.cop.batch.core.Status;
import br.com.sicoob.cro.cop.batch.job.Job;
import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe responsavel por iniciar o processo de execucao dos jobs.
 *
 * @author Rogerio Alves Rodrigues
 */
public class LauncherExecutor implements Callable<Boolean> {

    // Logger
    private static final Logger LOG = Logger.getLogger(LauncherExecutor.class.getName());
    // Configuracao do Processamento Batch.
    private final Object config;
    // Classe que contera os dados da execucao.
    private final Execution execution;
    // Classe que executa o job.
    @Inject
    private CoreExecution<Job, Boolean> jobExecution;

    /**
     * Construtor.
     *
     * @param execution Contexto de execucao.
     * @param config Dados de configuracao.
     */
    public LauncherExecutor(Execution execution, Object config) {
        this.execution = execution;
        this.config = config;
    }

    /**
     * Executa o processamento.
     *
     * @return Nulo - para compilacao.
     */
    @Override
    public Boolean call() {
        LOG.log(Level.INFO, "#### Iniciando o processamento ####");
        ((DataExecution) this.execution).setStatus(Status.RUNNING);
        ((DataExecution) this.execution).setResult(Result.SUCCESS);
        try {
            // injetando as dependencias de factory
            injectDependencies();

            // processa os jobs
            for (Job job : getConfiguration().getJobs()) {
                ((DataExecution) this.execution).setRunningJob(job);
                if (!this.jobExecution.execute(job)) {
                    ((DataExecution) this.execution).setRunningJob(null);
                    ((DataExecution) this.execution).setResult(Result.FAIL);
                    break;
                }
            }
        } catch (Exception erro) {
            LOG.log(Level.INFO, "\n#### Finalizando o processamento com erro ####");
            ((DataExecution) this.execution).setResult(Result.FAIL);
            ((DataExecution) this.execution).addErrorMessage(erro.getCause().getMessage());
            LOG.log(Level.SEVERE, erro.getMessage());
        } finally {
            ((DataExecution) this.execution).setStatus(Status.FINISHED);
            LOG.log(Level.INFO, "\n#### Finalizando o processamento ####");
        }
        return null;
    }

    /**
     * Obtem a classe de configuracao.
     *
     * @return um {@link BatchConfigurationResolver}.
     */
    private BatchConfigurationResolver getConfiguration() {
        return new BatchConfigurationResolver(this.config);
    }

    /**
     * Injeta as dependencias das factories.
     *
     * @throws IllegalArgumentException Erro.
     * @throws IllegalAccessException Erro.
     */
    private void injectDependencies() throws IllegalArgumentException, IllegalAccessException {
        // injetando as dependencias de job factory
        new JobFactoryInjector(this.config).inject();

        // injetando as dependencias de step factory
        new StepFactoryInjector(this.config).inject();
    }

}
