/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sicoob.cro.cop.batch.core.launcher;

import br.com.sicoob.cro.cop.batch.configuration.annotation.Inject;
import br.com.sicoob.cro.cop.batch.core.Result;
import br.com.sicoob.cro.cop.batch.core.IExecution;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
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
public class SimpleJobLauncher implements Launcher<IExecution> {

    // Configuracao do Processamento Batch.
    private final Object config;
    // Classe que contera os dados da execucao.
    @Inject
    private IExecution execution;

    /**
     * Constroi um {@link JobLauncher}.
     *
     * @param config Dados de configuracao.
     */
    public SimpleJobLauncher(Object config) {
        this.config = config;
    }

    /**
     * Implementa o metodo run da interface {@link Laucnher}.
     *
     * @return um {@link IExecution}. Sera o resultado do processamento como um
     * todo.
     */
    @Override
    public IExecution run() {
        ExecutorService executor = Executors.newFixedThreadPool(1);
        Callable<Boolean> launcherExecutor = new LauncherExecutor(this.execution, this.config);

        // cria uma thread para a execucao assincrona do processo
        FutureTask<Boolean> processTask = new FutureTask<>(launcherExecutor);
        executor.execute(processTask);

        // finaliza o executor
        executor.shutdown();
        return this.execution;
    }

}
