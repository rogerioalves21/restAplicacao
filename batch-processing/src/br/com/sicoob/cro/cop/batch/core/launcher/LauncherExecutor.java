/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sicoob.cro.cop.batch.core.launcher;

import static org.apache.commons.beanutils.PropertyUtils.setProperty;

import br.com.sicoob.cro.cop.batch.configuration.BatchConfigurations;
import br.com.sicoob.cro.cop.batch.configuration.BatchProcessModule;
import br.com.sicoob.cro.cop.batch.configuration.JobFactoryInjector;
import br.com.sicoob.cro.cop.batch.configuration.StepFactoryInjector;
import br.com.sicoob.cro.cop.batch.core.IJobExecutor;
import br.com.sicoob.cro.cop.batch.core.BatchExecution;
import br.com.sicoob.cro.cop.batch.core.JobDataExecution;
import br.com.sicoob.cro.cop.batch.core.Result;
import br.com.sicoob.cro.cop.batch.core.Status;
import br.com.sicoob.cro.cop.batch.job.Job;
import br.com.sicoob.cro.cop.batch.job.JobExecutor;
import br.com.sicoob.cro.cop.util.BatchKeys;
import br.com.sicoob.cro.cop.util.BatchPropertiesUtil;
import br.com.sicoob.cro.cop.util.JobFailsException;
import br.com.sicoob.cro.cop.util.Validation;
import com.google.inject.Guice;
import com.google.inject.Injector;
import java.util.concurrent.Callable;
import org.apache.commons.beanutils.ConstructorUtils;
import org.apache.commons.beanutils.MethodUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Classe responsavel por iniciar o processo de execucao dos jobs.
 *
 * @author Rogerio Alves Rodrigues
 */
public class LauncherExecutor implements Callable<Boolean> {

    // Logger
    private static final Log LOG = LogFactory.getLog(LauncherExecutor.class.getName());
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
     * @return TRUE - para compilacao.
     */
    public Boolean call() throws Exception {
        LOG.info(BatchPropertiesUtil.getInstance().getMessage(
                BatchKeys.BATCH_LAUNCHER_INITIALIZING.getKey()));
        try {
            setProperty(this.execution, BatchKeys.STATUS.getKey(), Status.STARTED);
            injectDependencies();
            executeJobs();
            setProperty(this.execution, BatchKeys.RESULT.getKey(), Result.SUCCESS);
            LOG.info(BatchPropertiesUtil.getInstance().getMessage(
                    BatchKeys.BATCH_LAUNCHER_ENDING.getKey()));
        } catch (Exception excecao) {
            LOG.fatal(Validation.getOr(excecao.getCause(),
                    excecao).getMessage(), excecao);
            LOG.info(BatchPropertiesUtil.getInstance().getMessage(
                    BatchKeys.BATCH_LAUNCHER_ERROR_ENDING.getKey()));
            setProperty(this.execution, BatchKeys.RUNNING_JOB.getKey(), null);
            setProperty(this.execution, BatchKeys.RESULT.getKey(), Result.FAIL);
            MethodUtils.invokeMethod(this.execution,
                    BatchKeys.ADD_ERROR_MESSAGE.getKey(), excecao);
        } finally {
            setProperty(this.execution,
                    BatchKeys.STATUS.getKey(), Status.COMPLETED);
        }
        return Boolean.TRUE;
    }

    /**
     * Itera e executa os jobs.
     *
     * @throws Exception quando houver algum erro.Ï
     */
    private void executeJobs() throws Exception {
        Injector injector = Guice.createInjector(new BatchProcessModule());
        for (Job job : getConfiguration().getJobs()) {
            setProperty(this.execution, BatchKeys.RUNNING_JOB.getKey(),
                    new JobDataExecution(job));
            IJobExecutor jobExecutor = injector.getInstance(JobExecutor.class);
            ((JobExecutor) jobExecutor).of(job).start();
            // verifica o resultado do job
            if (jobExecutor.fails()) {
                throw new JobFailsException(
                        BatchPropertiesUtil.getInstance().getMessage(
                                BatchKeys.BATCH_LAUNCHER_JOB_ERROR_ENDING.getKey(),
                                job.getId()));
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
     * @throws Exception Erro.
     */
    private void injectDependencies() throws Exception {
        ConstructorUtils.invokeConstructor(JobFactoryInjector.class, this.configurationObject).inject();
        ConstructorUtils.invokeConstructor(StepFactoryInjector.class, this.configurationObject).inject();
    }

}
