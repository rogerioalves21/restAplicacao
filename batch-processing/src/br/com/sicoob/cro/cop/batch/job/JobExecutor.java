/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sicoob.cro.cop.batch.job;

import br.com.sicoob.cro.cop.batch.service.BatchExecutorService;
import br.com.sicoob.cro.cop.batch.service.BatchExecutors;
import br.com.sicoob.cro.cop.batch.core.IJobExecutor;
import br.com.sicoob.cro.cop.batch.core.IStepExecutor;
import br.com.sicoob.cro.cop.batch.factory.Factory;
import br.com.sicoob.cro.cop.batch.factory.StepExecutorFactory;
import br.com.sicoob.cro.cop.batch.step.Step;
import br.com.sicoob.cro.cop.util.BatchKeys;
import br.com.sicoob.cro.cop.util.BatchPropertiesUtil;
import br.com.sicoob.cro.cop.util.JobFailsException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.FutureTask;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Classe responsavel por executar um job especifico.
 *
 * @author Rogerio Alves Rodrigues
 */
public class JobExecutor implements IJobExecutor {

    // Logger
    private static final Log LOG = LogFactory.getLog(JobExecutor.class.getName());

    // Job
    private Job job;

    // job helper
    private final JobExecutorHelper helper;

    // Executor do step (Fabrica)
    private final Factory<IStepExecutor> stepExecutorFactory;

    /**
     * Construtor.
     */
    public JobExecutor() {
        this.helper = new JobExecutorHelper();
        this.stepExecutorFactory = new StepExecutorFactory();
    }

    /**
     * Recebe um Job.
     *
     * @param job Job a ser executado.
     * @return a propria instancia.Ï
     */
    public JobExecutor of(Job job) {
        this.job = job;
        return this;
    }

    public void start() throws Exception {
        try {
            job.setStartTime(System.currentTimeMillis());
            job.setStatus(Job.Status.RUNNING);
            LOG.info(job.toString());
            List<FutureTask<Boolean>> asyncResults = new ArrayList();
            BatchExecutorService executor = null;
            if (job.getMode().equals(Job.Mode.ASYNC)) {
                executor = BatchExecutors.newFixedThreadPool(job.getSteps().size());
            } else if (job.getMode().equals(Job.Mode.SYNC)) {
                executor = BatchExecutors.newSingleThreadExecutor();
            }

            executeSteps(asyncResults, executor);
            executor.shutdown();

            helper.handleJobStatus(helper.successOnSteps(asyncResults), job);
        } catch (Exception excecao) {
            this.job.setStatus(Job.Status.FAIL);
            throw new JobFailsException(
                    BatchPropertiesUtil.getInstance().getMessage(
                            BatchKeys.BATCH_LAUNCHER_JOB_ERROR_ENDING.getKey(),
                            job.getId()), excecao);
        } finally {
            job.setEndTime(System.currentTimeMillis());
            LOG.info(BatchPropertiesUtil.getInstance().getMessage(
                    BatchKeys.BATCH_JOB_EXECUTOR_FINALIZED.getKey(),
                    job.getId()));
        }
    }

    /**
     * Realiza a execucao dos steps.
     *
     * @param asyncResults Lista para os resultados.
     * @param executor Servico de execucao.
     * @throws Exception para algum erro.
     */
    private void executeSteps(List<FutureTask<Boolean>> asyncResults,
            BatchExecutorService executor) throws Exception {
        for (Step step : job.getSteps()) {
            step.setJob(job);
            IStepExecutor stepExecutor = ((StepExecutorFactory) this.stepExecutorFactory)
                    .of(step)
                    .and(executor)
                    .create();
            stepExecutor.start();
            asyncResults.add(stepExecutor.getResult());
        }
    }

    /**
     * Devolve o status do job.
     *
     * @return um {@link Job.Status}.
     */
    public Job.Status getStatus() {
        return this.job.getStatus();
    }

    /**
     * Verifica se a execucoa do job falhou.
     *
     * @return Boolean.
     */
    public Boolean fails() {
        return this.getStatus().equals(Job.Status.FAIL);
    }

    public void notifyListeners() {
        // .TODO implementar a notificacao dos listeners.
    }

}
