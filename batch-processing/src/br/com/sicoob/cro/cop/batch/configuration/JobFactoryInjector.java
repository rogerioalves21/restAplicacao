/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sicoob.cro.cop.batch.configuration;

import br.com.sicoob.cro.cop.batch.configuration.annotation.JobBuilderFactory;
import br.com.sicoob.cro.cop.batch.factory.JobFactory;
import br.com.sicoob.cro.cop.util.BatchUtil;
import br.com.sicoob.cro.cop.util.Validation;
import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Injeta dependencias de job factory.
 *
 * @author Rogerio Alves Rodrigues
 */
public class JobFactoryInjector implements BatchInjector {

    // log
    private static final Logger LOG = Logger.getLogger(JobFactoryInjector.class.getName());

    // configuracao
    private final Object configuration;

    /**
     * Constroi um injector
     *
     * @param configuration Dados de configuracao.
     */
    public JobFactoryInjector(Object configuration) {
        this.configuration = configuration;
    }

    public void inject() throws Exception {
        Field[] fields = BatchUtil.getDeclaredFields(this.configuration);
        for (Field field : fields) {
            if (Validation.isFieldAnnotatedWith(field, JobBuilderFactory.class)) {
                LOG.log(Level.INFO, "Injetando a dependêcia [@JobBuilderFactory] para o atributo [".concat(field.getName()).concat("]"));
                field.setAccessible(Boolean.TRUE);
                field.set(this.configuration, JobFactory.get());
            }
        }
    }

}
