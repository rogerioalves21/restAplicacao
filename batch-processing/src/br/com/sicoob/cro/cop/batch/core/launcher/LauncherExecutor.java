/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sicoob.cro.cop.batch.core.launcher;

import br.com.sicoob.cro.cop.batch.configuration.BatchConfigurations;
import br.com.sicoob.cro.cop.batch.configuration.JobFactoryInjector;
import br.com.sicoob.cro.cop.batch.configuration.StepFactoryInjector;
import br.com.sicoob.cro.cop.batch.core.IJobExecutor;
import br.com.sicoob.cro.cop.batch.core.BatchExecution;
import br.com.sicoob.cro.cop.batch.core.DataExecution;
import br.com.sicoob.cro.cop.batch.core.JobDataExecution;
import br.com.sicoob.cro.cop.batch.core.JobExecution;
import br.com.sicoob.cro.cop.batch.core.Result;
import br.com.sicoob.cro.cop.batch.core.Status;
import br.com.sicoob.cro.cop.batch.job.Job;
import br.com.sicoob.cro.cop.batch.job.JobExecutor;
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
    private final Object configurationObject;
    // Classe que contera os dados da execucao.
    private final BatchExecution execution;

    /**
     * Construtor.
     *
     * @param execution Contexto de execucao.
     * @param configurationObject Dados de configuracao.
     */
    public LauncherExecutor(BatchExecution execution, Object configurationObject) {
        this.execution = execution;
        this.configurationObject = configurationObject;
    }

    /**
     * Executa o processamento.
     *
     * @return Nulo - para compilacao.
     */
    public Boolean call() {
        LOG.log(Level.INFO, "#### Iniciando o processamento ####");
        ((DataExecution) this.execution).setStatus(Status.STARTED);
        try {
            // injetando as dependencias de factory dos jobs e steps
            injectDependencies();

            // processa os jobs
            for (Job job : getConfiguration().getJobs()) {
                // adiciona o job que sera executado
                ((DataExecution) this.execution).setRunningJob(new JobDataExecution(job));

                // cria um Job Executor
                IJobExecutor jobExecutor = new JobExecutor(job);

                // inicia a execucao do job
                jobExecutor.start();

                // verifica o resultado do job
                if (jobExecutor.getStatus().equals(Job.Status.FAIL)) {
                    ((DataExecution) this.execution).setRunningJob(null);
                    ((DataExecution) this.execution).setResult(Result.FAIL);
                    break;
                }
            }
            ((DataExecution) this.execution).setResult(Result.SUCCESS);
        } catch (Exception erro) {
            LOG.log(Level.INFO, "\n#### Finalizando o processamento com erro ####");
            ((DataExecution) this.execution).setResult(Result.FAIL);
            ((DataExecution) this.execution).addErrorMessage(erro.getCause().getMessage());
            LOG.log(Level.SEVERE, erro.getMessage());
        } finally {
            ((DataExecution) this.execution).setStatus(Status.COMPLETED);
            LOG.log(Level.INFO, "\n#### Finalizando o processamento ####");
        }
        return null;
    }

    /**
     * Obtem a classe de configuracao.
     *
     * @return um {@link BatchConfigurations}.
     */
    private BatchConfigurations getConfiguration() {
        return new BatchConfigurations(this.configurationObject);
    }

    /**
     * Injeta as dependencias das factories.
     *
     * @throws IllegalArgumentException Erro.
     * @throws IllegalAccessException Erro.
     */
    private void injectDependencies() throws IllegalArgumentException, IllegalAccessException {
        // injetando as dependencias de job factory
        new JobFactoryInjector(this.configurationObject).inject();

        // injetando as dependencias de step factory
        new StepFactoryInjector(this.configurationObject).inject();
    }

}
