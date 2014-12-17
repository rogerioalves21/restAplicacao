/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sicoob.cro.cop.batch.core.launcher;

import br.com.sicoob.cro.cop.batch.core.BatchCallable;
import static org.apache.commons.beanutils.PropertyUtils.setProperty;

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
import org.apache.commons.beanutils.MethodUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Classe responsavel por iniciar o processo de execucao dos jobs.
 *
 * @author Rogerio Alves Rodrigues
 */
public class LauncherExecutor implements BatchCallable {

    // Logger
    private static final Log LOG = LogFactory.getLog(LauncherExecutor.class.getName());
    // Classe que contera os dados da execucao.
    private final BatchExecution execution;
    // Job para execucao
    private final Job job;

    /**
     * Construtor.
     *
     * @param execution Contexto de execucao.
     * @param job Job para execucao.
     */
    public LauncherExecutor(BatchExecution execution, Job job) {
        this.execution = execution;
        this.job = job;
    }

    /**
     * Executa o processamento.
     *
     * @return TRUE - para compilacao.
     * @throws java.lang.Exception par algum erro.
     */
    public Boolean call() throws Exception {
        LOG.info(BatchPropertiesUtil.getInstance().getMessage(
                BatchKeys.BATCH_LAUNCHER_INITIALIZING.getKey()));
        try {
            setProperty(this.execution, BatchKeys.STATUS.getKey(), Status.STARTED);
            executeJob();
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
    private void executeJob() throws Exception {
        setProperty(this.execution, BatchKeys.RUNNING_JOB.getKey(),
                new JobDataExecution(this.job));
        IJobExecutor jobExecutor = new JobExecutor();
        ((JobExecutor) jobExecutor).of(this.job).start();
        if (jobExecutor.fails()) {
            throw new JobFailsException(
                    BatchPropertiesUtil.getInstance().getMessage(
                            BatchKeys.BATCH_LAUNCHER_JOB_ERROR_ENDING.getKey(),
                            this.job.getId()));
        }
    }

}
