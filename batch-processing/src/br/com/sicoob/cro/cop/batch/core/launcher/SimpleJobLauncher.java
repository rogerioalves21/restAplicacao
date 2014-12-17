/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sicoob.cro.cop.batch.core.launcher;

import br.com.sicoob.cro.cop.batch.core.Result;
import br.com.sicoob.cro.cop.batch.core.BatchExecution;
import br.com.sicoob.cro.cop.batch.job.Job;
import br.com.sicoob.cro.cop.batch.service.BatchExecutorService;
import br.com.sicoob.cro.cop.batch.service.BatchExecutors;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * Launcher.
 *
 * Responsavel por executar os jobs a ele anexados.
 *
 * Os Jobs podem ser assincronos ou sincronos.
 *
 * Para os assincronos sera utilizados a classe {@link FutureTask} do java 6.
 *
 * Para os sincronos sera executado o metodo call.
 *
 * Todos os tasklets deveracao retornar a classe {@link Result} para o sistema
 * afim de verificar sua finalizacao.
 *
 * Recebe uma classe de configuracao que contem os dados para a execucao.
 *
 * @author Rogerio Alves Rodrigues
 */
public class SimpleJobLauncher implements Launcher {

    // Classe para o acompanhamento de execucao
    private final BatchExecution execution;

    /**
     * Construtor.
     *
     * @param execution Retorno da execucao do Batch.
     */
    public SimpleJobLauncher(BatchExecution execution) {
        this.execution = execution;
    }

    public BatchExecution run(Job job) {
        BatchExecutorService executorService = BatchExecutors.newFixedThreadPool(1);
        Callable<Boolean> launcherExecutor = new LauncherExecutor(this.execution, job);

        // cria uma thread para a execucao assincrona do processo
        FutureTask<Boolean> processTask = new FutureTask(launcherExecutor);
        executorService.executeTask(processTask);

        // finaliza o executor
        executorService.shutdown();
        return this.execution;
    }

}
