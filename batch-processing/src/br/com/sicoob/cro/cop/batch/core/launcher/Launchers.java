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

    // configuracao do batch
    private final Object configurationObject;
    private final Job job;

    /**
     * Construtor privado.
     *
     * @param configurationObject Configuracao do batch.
     */
    private Launchers(Object configurationObject, Job job) {
        this.configurationObject = configurationObject;
        this.job = job;
    }

    /**
     * Retorna uma nota instancia do launcher factory.
     *
     * @param configurationObject Configuracao do batch.
     * @return um {@link Launchers}.
     */
    public static Launchers get(Object configurationObject) {
        return new Launchers(configurationObject, null);
    }

    /**
     * Retorna uma nota instancia do launcher factory.
     *
     * @param job Job a ser executado.
     * @return um {@link Launchers}.
     */
    public static Launchers get(Job job) {
        return new Launchers(null, job);
    }

    /**
     * Cria um Laucnher.
     *
     * @return um {@link BatchProcess}.
     */
    public BatchProcess create() {
        Injector injector = Guice.createInjector(new BatchProcessModule());
        BatchProcess process = injector.getInstance(BatchProcess.class);
        process.addConfigurationObject(this.configurationObject);
        process.addJob(this.job);
        return process;
    }

}
