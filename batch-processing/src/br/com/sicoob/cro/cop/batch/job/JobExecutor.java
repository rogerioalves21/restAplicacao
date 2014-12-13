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
import com.google.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe responsavel por executar um job especifico.
 *
 * @author Rogerio Alves Rodrigues
 */
public class JobExecutor implements IJobExecutor {

    // Logger
    private static final Logger LOG = Logger.getLogger(JobExecutor.class.getName());

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
     * @param job Job a ser executado.
     * @return a propria instancia.Ï
     */
    public JobExecutor of(Job job) {
        this.job = job;
        return this;
    }
    
    /**
     * Printa os dados do logÏ
     */
    private void logJobData() {
        LOG.log(Level.INFO, "Job: ".concat(this.job.getId()));
        LOG.log(Level.INFO, "Quantidade de steps: ".concat(String.valueOf(this.job.getSteps().size())));
        LOG.log(Level.INFO, "Modo de execucao do Job: ".concat(this.job.getMode().name()));
    }

    public void start() throws Exception {
        this.job.setStatus(Job.Status.RUNNING);
        logJobData();
        List<FutureTask<Result>> asyncResults = new ArrayList();
        BatchExecutorService executor = BatchExecutors.newSingleThreadExecutor();
        
        if (this.job.getMode().equals(Job.Mode.ASYNC)) {
            executor = BatchExecutors.newFixedThreadPool(this.job.getSteps().size());
        }

        // executa os steps e os adiciona na lista para verificacao posterior
        for (Step step : this.job.getSteps()) {
            IStepExecutor stepExecutor = ((StepExecutorFactory) this.stepExecutorFactory)
                    .of(step)
                    .and(executor)
                    .create();
            stepExecutor.start();
            asyncResults.add(stepExecutor.getResult());
        }

        // finaliza o executor
        executor.shutdown();

        // verifica o resultado dos steps rodados
        if (successOnSteps(asyncResults)) {
            this.job.setStatus(Job.Status.FINISHED);
        } else {
            this.job.setStatus(Job.Status.FAIL);
        }

        LOG.log(Level.INFO, "Job ".concat(job.getId()).concat(" finalizado"));
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
    private Boolean successOnSteps(List<FutureTask<Result>> asyncResults) throws InterruptedException, ExecutionException {
        Boolean isRunning = Boolean.TRUE;
        while (isRunning) {
            isRunning = Boolean.FALSE;
            for (FutureTask<Result> task : asyncResults) {
                if (!task.isDone()) {
                    isRunning = Boolean.TRUE;
                }
            }
        }

        for (FutureTask<Result> task : asyncResults) {
            if (task.get().getType().equals(Result.Type.FAIL)) {
                return Boolean.FALSE;
            }
        }

        return Boolean.TRUE;
    }

    public Job.Status getStatus() {
        return this.job.getStatus();
    }

    public Boolean fails() {
        return this.getStatus().equals(Job.Status.FAIL);
    }

}
