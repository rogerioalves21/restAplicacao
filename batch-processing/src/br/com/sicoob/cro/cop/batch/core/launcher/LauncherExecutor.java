/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sicoob.cro.cop.batch.core.launcher;

import br.com.sicoob.cro.cop.batch.configuration.BatchConfigurations;
import br.com.sicoob.cro.cop.batch.configuration.BatchProcessModule;
import br.com.sicoob.cro.cop.batch.configuration.JobFactoryInjector;
import br.com.sicoob.cro.cop.batch.configuration.StepFactoryInjector;
import br.com.sicoob.cro.cop.batch.core.IJobExecutor;
import br.com.sicoob.cro.cop.batch.core.BatchExecution;
import br.com.sicoob.cro.cop.batch.core.DataExecution;
import br.com.sicoob.cro.cop.batch.core.JobDataExecution;
import br.com.sicoob.cro.cop.batch.core.Result;
import br.com.sicoob.cro.cop.batch.core.Status;
import br.com.sicoob.cro.cop.batch.job.Job;
import br.com.sicoob.cro.cop.batch.job.JobExecutor;
import com.google.inject.Guice;
import com.google.inject.Injector;
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
            injectDependencies();
            executeJobs();
            ((DataExecution) this.execution).setResult(Result.SUCCESS);
        } catch (Exception erro) {
            LOG.log(Level.INFO, "\n#### Finalizando o processamento com erro ####");
            ((DataExecution) this.execution).setResult(Result.FAIL);
            ((DataExecution) this.execution).addErrorMessage(erro.getCause().getMessage());
            LOG.log(Level.SEVERE, erro.getCause().getMessage());
        } finally {
            ((DataExecution) this.execution).setStatus(Status.COMPLETED);
            LOG.log(Level.INFO, "\n#### Finalizando o processamento ####");
        }
        return null;
    }

    /**
     * Itera e executa os jobs.
     *
     * @throws Exception quando houver algum erro.Ï
     */
    private void executeJobs() throws Exception {
        Injector injector = Guice.createInjector(new BatchProcessModule());
        for (Job job : getConfiguration().getJobs()) {
            ((DataExecution) this.execution).setRunningJob(new JobDataExecution(job));
            IJobExecutor jobExecutor = injector.getInstance(JobExecutor.class);
            ((JobExecutor) jobExecutor).of(job).start();
            // verifica o resultado do job
            if (jobExecutor.getStatus().equals(Job.Status.FAIL)) {
                ((DataExecution) this.execution).setRunningJob(null);
                ((DataExecution) this.execution).setResult(Result.FAIL);
                break;
            }
        }
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
        new JobFactoryInjector(this.configurationObject).inject();
        new StepFactoryInjector(this.configurationObject).inject();

    }

}
