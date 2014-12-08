/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sicoob.cro.cop.batch.configuration;

import br.com.sicoob.cro.cop.batch.configuration.annotation.Inject;
import br.com.sicoob.cro.cop.batch.core.launcher.LauncherExecutor;
import br.com.sicoob.cro.cop.batch.job.JobExecution;
import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Injeta as dependencias para um {@link LauncherExecutor}.
 * @author rogerioalves21
 */
public class LauncherExecutorInjector implements Injector {

    // log
    private static final Logger LOG = Logger.getLogger(LauncherExecutorInjector.class.getName());
    // injetavel
    private final LauncherExecutor launcher;

    public LauncherExecutorInjector(LauncherExecutor launcher) {
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
                field.set(this.launcher, new JobExecution());
            }
        }
    }

}
