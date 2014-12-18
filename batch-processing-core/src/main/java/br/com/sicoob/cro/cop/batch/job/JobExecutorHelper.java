/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sicoob.cro.cop.batch.job;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Metodos de ajuda para o JobExecutor.Ï
 *
 * @author Rogerio Alves Rodrigues
 */
public class JobExecutorHelper {

    /**
     * Verifica os resultados dos steps. Caso algum esteja com erro/falha
     * retorna falso, caso contrario verdadeiro.Ï
     *
     * @param asyncResults Lista de resultados de steps assincronos.
     * @return Resultado da verificacao.
     * @throws InterruptedException Erro de interrupcao de thread.
     * @throws ExecutionException Erro de execucao de thread.
     */
    public Boolean successOnSteps(List<FutureTask<Boolean>> asyncResults) throws InterruptedException, ExecutionException {
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
     * Trata o status do job de acordo com sua finalização.
     *
     * @param jobSuccess Resultado da execucao do job.
     * @param job Job para alteracao.
     */
    public void handleJobStatus(Boolean jobSuccess, Job job) {
        if (jobSuccess && !job.getStatus().equals(Job.Status.FAIL)) {
            job.setStatus(Job.Status.FINISHED);
        } else {
            job.setStatus(Job.Status.FAIL);
        }
    }
}
