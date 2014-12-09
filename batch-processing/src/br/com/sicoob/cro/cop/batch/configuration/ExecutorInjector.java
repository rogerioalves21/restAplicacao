/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sicoob.cro.cop.batch.configuration;

import br.com.sicoob.cro.cop.batch.configuration.annotation.Inject;
import br.com.sicoob.cro.cop.batch.core.IExecution;
import br.com.sicoob.cro.cop.batch.core.DataExecution;
import br.com.sicoob.cro.cop.batch.core.launcher.Launcher;
import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Injeta as dependencias para um Launcher.
 *
 * @author Rogerio Alves Rodrigues
 */
public class ExecutorInjector implements Injector {

    // log
    private static final Logger LOG = Logger.getLogger(ExecutorInjector.class.getName());
    // injetavel
    private final Launcher<IExecution> launcher;

    /**
     * Construtor.
     *
     * @param launcher Injetavel.
     */
    public ExecutorInjector(Launcher<IExecution> launcher) {
        this.launcher = launcher;
    }

    @Override
    public void inject() throws IllegalArgumentException, IllegalAccessException {
        // obtem os campos do tasklet
        Field[] fields = this.launcher.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Inject.class)) {
                LOG.log(Level.INFO, "Injetando a depencia [@Inject] para o atributo [".concat(field.getName()).concat("]"));
                field.setAccessible(Boolean.TRUE);
                field.set(this.launcher, new DataExecution());
            }
        }
    }

}
