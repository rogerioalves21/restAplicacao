/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sicoob.cro.cop.batch.configuration;

import br.com.sicoob.cro.cop.batch.configuration.annotation.Context;
import br.com.sicoob.cro.cop.batch.step.Step;
import br.com.sicoob.cro.cop.batch.step.tasklet.TaskletContext;
import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Injecao de dependencias para o tasklet.
 *
 * @author Rogerio Alves Rodrigues
 */
public class TaskletInjector implements Injector {

    // log
    private static final Logger LOG = Logger.getLogger(TaskletInjector.class.getName());
    
    // step
    private final Step step;

    /**
     * Constroi o tasklet injecto.
     *
     * @param step step com os parametros.
     */
    public TaskletInjector(Step step) {
        this.step = step;
    }

    /**
     * Injeta a dependencia do contexto no takslet.
     *
     * @throws IllegalArgumentException para argumento ilegal.
     * @throws IllegalAccessException para acesso ilegal.
     */
    @Override
    public void inject() throws IllegalArgumentException, IllegalAccessException {
        // obtem os campos do tasklet
        Field[] fields = this.step.getTasklet().getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Context.class)) {
                LOG.log(Level.INFO, "Injetando a depencia [@TaskletContext] para o atributo [".concat(field.getName()).concat("]"));
                field.setAccessible(Boolean.TRUE);
                field.set(this.step.getTasklet(), createContext());
            }
        }
    }

    /**
     * Cria o contexto de dados para o tasklet.
     *
     * @return um {@link TaskletContext}.
     */
    private TaskletContext createContext() {
        TaskletContext context = new TaskletContext();
        context.setParameters(this.step.getParameters());
        return context;
    }

}
