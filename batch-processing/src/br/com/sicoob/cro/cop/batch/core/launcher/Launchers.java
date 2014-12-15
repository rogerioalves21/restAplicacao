/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sicoob.cro.cop.batch.core.launcher;

import br.com.sicoob.cro.cop.batch.configuration.BatchProcessModule;
import br.com.sicoob.cro.cop.batch.core.BatchProcess;
import br.com.sicoob.cro.cop.batch.factory.Factory;
import br.com.sicoob.cro.cop.batch.job.Job;
import com.google.inject.Guice;
import com.google.inject.Injector;

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
        Injector injector = Guice.createInjector(new BatchProcessModule());
        BatchProcess process = injector.getInstance(BatchProcess.class);
        process.addJob(this.job);
        return process;
    }

}
