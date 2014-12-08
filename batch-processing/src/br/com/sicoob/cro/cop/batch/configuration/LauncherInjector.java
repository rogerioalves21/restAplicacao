/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sicoob.cro.cop.batch.configuration;

import br.com.sicoob.cro.cop.batch.configuration.annotation.Inject;
import br.com.sicoob.cro.cop.batch.core.Execution;
import br.com.sicoob.cro.cop.batch.core.launcher.BatchProcess;
import br.com.sicoob.cro.cop.batch.core.launcher.Launcher;
import br.com.sicoob.cro.cop.batch.core.launcher.SimpleJobLauncher;
import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Injecao de dependencia para para o objeto {@link BatchProcess}.
 *
 * @author Rogerio Alves Rodrigues
 */
public class LauncherInjector implements Injector {

    // log
    private static final Logger LOG = Logger.getLogger(LauncherInjector.class.getName());

    // configuracao
    private final BatchProcess configuration;

    // Launcher config
    private final Object launcherConfiguration;

    /**
     * Constroi um injector.
     *
     * @param configuration Dados de configuracao.
     * @param launcherConfig Dados de parametro para o simple Job launcher.
     */
    public LauncherInjector(BatchProcess configuration, Object launcherConfig) {
        this.configuration = configuration;
        this.launcherConfiguration = launcherConfig;
    }

    @Override
    public void inject() throws IllegalArgumentException, IllegalAccessException {
        // obtem os campos do tasklet
        Field[] fields = this.configuration.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Inject.class)) {
                LOG.log(Level.INFO, "Injetando a depencia [@Inject] para o atributo [".concat(field.getName()).concat("]"));
                field.setAccessible(Boolean.TRUE);
                field.set(this.configuration, createJobLauncher());
            }
        }
    }

    /**
     * Cria um {@link Launcher} e injeta suas dependencias.
     *
     * @return um {@link Launcher}.
     * @throws IllegalArgumentException Erro.
     * @throws IllegalAccessException Erro.
     */
    private Launcher<Execution> createJobLauncher() throws IllegalArgumentException, IllegalAccessException {
        Launcher<Execution> simpleJob = new SimpleJobLauncher(this.launcherConfiguration);
        new ExecutorInjector(simpleJob).inject();
        return simpleJob;
    }

}
