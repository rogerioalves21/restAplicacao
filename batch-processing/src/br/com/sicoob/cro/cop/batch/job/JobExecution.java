/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sicoob.cro.cop.batch.job;

import br.com.sicoob.cro.cop.batch.core.CoreExecution;
import br.com.sicoob.cro.cop.batch.core.Result;
import br.com.sicoob.cro.cop.batch.step.Step;
import br.com.sicoob.cro.cop.batch.step.StepExecution;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe responsavel por executar um job especifico.
 *
 * @author Rogerio Alves Rodrigues
 */
public class JobExecution implements CoreExecution<Job, Boolean> {

    // Logger
    private static final Logger LOG = Logger.getLogger(JobExecution.class.getName());
    // Tempo de espera para verificar se os tasks ja rodaram.
    private static final long WAIT_TIME = 2000;
    // Executor de steps
    private final CoreExecution<Step, FutureTask<Result>> stepExecution = new StepExecution();

    @Override
    public Boolean execute(Job job) throws Exception {
        job.setStatus(Job.Status.RUNNING);

        List<FutureTask<Result>> asyncResults = new ArrayList();

        // Cria um pool de threads do tamanho da lista de steps
        ExecutorService executor = Executors.newSingleThreadExecutor();

        if (job.getMode().equals(Job.Mode.ASYNC)) {
            executor = Executors.newFixedThreadPool(job.getSteps().size());
        }

        LOG.log(Level.INFO, "Job: ".concat(job.getNome()));
        LOG.log(Level.INFO, "Quantidade de steps: ".concat(String.valueOf(job.getSteps().size())));
        LOG.log(Level.INFO, "Modo de execucao do Job: ".concat(job.getMode().name()));

        // obtem os steps
        List<Step> steps = job.getSteps();

        // executa os steps e os adiciona na lista para verificacao posterior
        for (Step step : steps) {
            asyncResults.add(this.stepExecution.execute(step, executor));
        }

        // verifica se os steps enviados para execucao ainda estao rodando
        Boolean stepsIsRunning = Boolean.TRUE;
        while (stepsIsRunning) {
            // espera um tempo antes de verificar novamente
            Thread.sleep(WAIT_TIME);
            stepsIsRunning = executor.isTerminated();
        }

        // verifica o resultado dos steps rodados
        Boolean result = verifyStepsResults(asyncResults);

        if (result) {
            job.setStatus(Job.Status.FINISHED);
        } else {
            job.setStatus(Job.Status.FAIL);
        }

        LOG.log(Level.INFO, "Job ".concat(job.getNome()).concat(" finalizado"));
        return result;
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
    private Boolean verifyStepsResults(List<FutureTask<Result>> asyncResults) throws InterruptedException, ExecutionException {
        for (FutureTask<Result> task : asyncResults) {
            if (task.get().getType().equals(Result.Type.FAIL)) {
                return Boolean.FALSE;
            }
        }
        return Boolean.TRUE;
    }

    @Override
    public Boolean execute(Job item, ExecutorService executor) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
