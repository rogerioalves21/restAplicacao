/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sicoob.cro.cop.batch.core;

import br.com.sicoob.cro.cop.batch.core.launcher.Launcher;
import br.com.sicoob.cro.cop.batch.job.Job;
import com.google.inject.Inject;

/**
 * Classe que sera retornada para o cliente.
 *
 * @author Rogerio Alves Rodrigues
 */
public class BatchProcess {

    // Classe executora
    private final Launcher execution;
    // Job a ser executado
    private Job job;

    /**
     * Construtor.
     *
     * @param execution Classe o lancamento da execucao.
     */
    @Inject
    public BatchProcess(Launcher execution) {
        this.execution = execution;
    }

    /**
     * Adiciona o job a ser executado.
     *
     * @param job Job.
     */
    public void addJob(Job job) {
        this.job = job;
    }

    /**
     * Executa o processamento.
     *
     * @return um {@link BatchExecution}.
     */
    public BatchExecution start() {
        return this.execution.run(this.job);
    }

}
