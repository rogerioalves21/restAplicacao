/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sicoob.cro.cop.batch.core.launcher;

import br.com.sicoob.cro.cop.batch.configuration.LauncherExecutorInjector;
import br.com.sicoob.cro.cop.batch.configuration.annotation.Inject;
import br.com.sicoob.cro.cop.batch.core.Result;
import br.com.sicoob.cro.cop.batch.core.Execution;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.logging.Level;
import java.util.logging.Logger;

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
public class SimpleJobLauncher implements Launcher<Execution> {

    // Configuracao do Processamento Batch.
    private final Object config;
    // Classe que contera os dados da execucao.
    @Inject
    private Execution execution;

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
     * @return um {@link Execution}. Sera o resultado do processamento como um
     * todo.
     */
    @Override
    public Execution run() {
        ExecutorService executor = Executors.newFixedThreadPool(1);
        Callable<Boolean> launcherExecutor = new LauncherExecutor(this.execution, this.config);

        // injetando as dependencias
        injectDependencies(launcherExecutor);

        FutureTask<Boolean> task = new FutureTask<>(launcherExecutor);
        executor.execute(task);
        return this.execution;
    }

    /**
     * Injetor de dependencias.
     *
     * @param launcherExecutor Executor.
     */
    private void injectDependencies(Callable<Boolean> launcherExecutor) {
        try {
            new LauncherExecutorInjector((LauncherExecutor) launcherExecutor).inject();
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(SimpleJobLauncher.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(SimpleJobLauncher.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
