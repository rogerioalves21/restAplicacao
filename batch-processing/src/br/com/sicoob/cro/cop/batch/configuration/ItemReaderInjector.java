/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sicoob.cro.cop.batch.configuration;

import br.com.sicoob.cro.cop.batch.configuration.annotation.Context;
import br.com.sicoob.cro.cop.batch.step.Step;
import br.com.sicoob.cro.cop.batch.step.StepParameters;
import br.com.sicoob.cro.cop.batch.step.chunk.ChunkContext;
import br.com.sicoob.cro.cop.batch.step.tasklet.TaskletContext;
import br.com.sicoob.cro.cop.util.BatchUtil;
import br.com.sicoob.cro.cop.util.Validation;
import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.beanutils.ConstructorUtils;
import org.apache.commons.beanutils.PropertyUtils;

/**
 *
 * @author rogerioalves21
 */
public class ItemReaderInjector implements BatchInjector {

    // log
    private static final Logger LOG = Logger.getLogger(ItemReaderInjector.class.getName());

    // step
    private final Step step;

    /**
     * Constroi o tasklet injecto.
     *
     * @param step step com os parametros.
     */
    public ItemReaderInjector(Step step) {
        this.step = step;
    }

    public void inject() throws Exception {
        Field[] fields = BatchUtil.getDeclaredFields(this.step.getReader());
        for (Field field : fields) {
            if (Validation.isFieldAnnotatedWith(field, Context.class)) {
                LOG.log(Level.INFO, "Injetando a dependêcia [@Context] para o atributo [".concat(field.getName()).concat("]"));
                field.setAccessible(Boolean.TRUE);
                field.set(this.step.getReader(), createContext());
            }
        }
    }

    /**
     * Cria o contexto de dados para o tasklet.
     *
     * @return um {@link TaskletContext}.
     */
    private ChunkContext createContext() throws Exception {
        return ConstructorUtils.invokeConstructor(ChunkContext.class,
                (StepParameters) PropertyUtils.getProperty(this.step, "parameters"));
    }

}
