/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sicoob.cro.cop.batch.core.launcher;

import br.com.sicoob.cro.cop.batch.core.BatchProcess;
import br.com.sicoob.cro.cop.batch.core.DataExecution;
import br.com.sicoob.cro.cop.batch.factory.Factory;
import br.com.sicoob.cro.cop.batch.job.Job;

/**
 * Metodos utilitarios para trabalhar com launchers.
 *
 * @author Rogerio Alves Rodrigues
 */
public class Launchers implements Factory<BatchProcess> {

    private final Job job;

    /**
     * Construtor privado.
     *
     * @param job Job para execucao.
     */
    public Launchers(Job job) {
        this.job = job;
    }

    /**
     * Cria um Laucnher.
     *
     * @return um {@link BatchProcess}.
     */
    public BatchProcess create() {
        BatchProcess process = new BatchProcess(new SimpleJobLauncher(new DataExecution()));
        process.addJob(this.job);
        return process;
    }

}
