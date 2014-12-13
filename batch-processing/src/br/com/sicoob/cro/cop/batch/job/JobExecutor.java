/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sicoob.cro.cop.batch.job;

import br.com.sicoob.cro.cop.batch.service.BatchExecutorService;
import br.com.sicoob.cro.cop.batch.service.BatchExecutors;
import br.com.sicoob.cro.cop.batch.configuration.annotation.FactoryStepExecutor;
import br.com.sicoob.cro.cop.batch.core.IJobExecutor;
import br.com.sicoob.cro.cop.batch.core.IStepExecutor;
import br.com.sicoob.cro.cop.batch.core.Result;
import br.com.sicoob.cro.cop.batch.factory.Factory;
import br.com.sicoob.cro.cop.batch.factory.StepExecutorFactory;
import br.com.sicoob.cro.cop.batch.step.Step;
import br.com.sicoob.cro.cop.util.JobFailsException;
import com.google.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
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

    // Executor do step (Fabrica)
    @Inject
    @FactoryStepExecutor
    private Factory<IStepExecutor> stepExecutorFactory;

    /**
     * Construtor.
     */
    public JobExecutor() {

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

    /**
     * Printa os dados do log
     */
    private void logJobData() {
        LOG.info("Job: ".concat(this.job.getId()));
        LOG.info("Quantidade de steps: ".concat(String.valueOf(this.job.getSteps().size())));
        LOG.info("Modo de execucao do Job: ".concat(this.job.getMode().name()));
    }

    public void start() throws Exception {
        this.job.setStatus(Job.Status.RUNNING);
        logJobData();
        List<FutureTask<Boolean>> asyncResults = new ArrayList();
        BatchExecutorService executor = BatchExecutors.newSingleThreadExecutor();

        if (this.job.getMode().equals(Job.Mode.ASYNC)) {
            executor = BatchExecutors.newFixedThreadPool(this.job.getSteps().size());
        }

        // executa os steps e os adiciona na lista para verificacao posterior
        executeSteps(asyncResults, executor);

        // finaliza o executor
        executor.shutdown();

        try {
            // verifica o resultado dos steps rodados
            if (successOnSteps(asyncResults)) {
                this.job.setStatus(Job.Status.FINISHED);
            } else {
                this.job.setStatus(Job.Status.FAIL);
            }
        } catch (Exception excecao) {
            this.job.setStatus(Job.Status.FAIL);
            throw new JobFailsException("O Job [" + this.job.getId() + "] falhou em sua execucao", excecao);
        } finally {
            LOG.info("Job ".concat(job.getId()).concat(" finalizado"));
        }
    }

    /**
     * Realiza a execucao dos steps.
     *
     * @param asyncResults Lista para os resultados.
     * @param executor Servico de execucao.
     * @throws Exception para algum erro.
     */
    private void executeSteps(List<FutureTask<Boolean>> asyncResults, BatchExecutorService executor) throws Exception {
        for (Step step : this.job.getSteps()) {
            step.setJob(this.job);
            IStepExecutor stepExecutor = ((StepExecutorFactory) this.stepExecutorFactory)
                    .of(step)
                    .and(executor)
                    .create();
            stepExecutor.start();
            asyncResults.add(stepExecutor.getResult());
        }
    }

    /**
     * Verifica os resultados dos steps. Caso algum esteja com erro/falha
     * retorna falso, caso contrario verdadeiro.Ï
     *
     * @param asyncResults Lista de resultados de steps assincronos.
     * @return Resultado da verificacao.
     * @throws InterruptedException Erro de interrupcao de thread.
     * @throws ExecutionException Erro de execucao de thread.
     */
    private Boolean successOnSteps(List<FutureTask<Boolean>> asyncResults) throws InterruptedException, ExecutionException {
        Boolean isRunning = Boolean.TRUE;
        while (isRunning) {
            isRunning = Boolean.FALSE;
            for (FutureTask<Boolean> task : asyncResults) {
                if (!task.isDone()) {
                    isRunning = Boolean.TRUE;
                }
            }
        }
        return Boolean.TRUE;
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

}
