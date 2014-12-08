/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sicoob.cro.cop.batch.configuration;

import br.com.sicoob.cro.cop.batch.configuration.annotation.StepBuilderFactory;
import br.com.sicoob.cro.cop.batch.factory.StepFactory;
import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Injeta dependencias de step factory.
 *
 * @author Rogerio Alves Rodrigues
 */
public class StepFactoryInjector implements Injector {

    // log
    private static final Logger LOG = Logger.getLogger(StepFactoryInjector.class.getName());

    // configuracao
    private final Object configuration;

    /**
     * Constroi um injector
     *
     * @param configuration Dados de configuracao.
     */
    public StepFactoryInjector(Object configuration) {
        this.configuration = configuration;
    }

    @Override
    public void inject() throws IllegalArgumentException, IllegalAccessException {
        // obtem os campos do tasklet
        Field[] fields = this.configuration.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(StepBuilderFactory.class)) {
                StepBuilderFactory annotation = field.getAnnotation(StepBuilderFactory.class);
                LOG.log(Level.INFO, "Injetando a depencia [@StepBuilderFactory] para o atributo [".concat(field.getName()).concat("]"));
                field.setAccessible(Boolean.TRUE);
                field.set(this.configuration, StepFactory.get(annotation.type()));
            }
        }
    }

}
