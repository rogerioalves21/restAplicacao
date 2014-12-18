/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sicoob.cro.cop.batch.factory;

import br.com.sicoob.cro.cop.batch.configuration.AbstractItemProcessor;
import br.com.sicoob.cro.cop.batch.configuration.AbstractItemReader;
import br.com.sicoob.cro.cop.batch.configuration.AbstractItemWriter;
import br.com.sicoob.cro.cop.batch.step.Step;
import br.com.sicoob.cro.cop.batch.step.StepParameters;
import br.com.sicoob.cro.cop.batch.step.tasklet.AbstractTasklet;

/**
 * Fabrica de Steps.
 *
 * Responsavel pela criacao de passos de uma tarefa.
 *
 * @author Rogerio Alves Rodrigues
 */
public final class StepFactory implements Factory<Step> {

    private AbstractTasklet tasklet;
    private AbstractItemReader reader;
    private AbstractItemProcessor processor;
    private AbstractItemWriter writer;
    private final Step.Type type;
    private StepParameters parameters;
    private Integer commitInterval;

    public StepFactory reader(AbstractItemReader reader) {
        this.reader = reader;
        return this;
    }
    
    public StepFactory commitInterval(Integer commitInterval) {
        this.commitInterval = commitInterval;
        return this;
    }
    
    public StepFactory processor(AbstractItemProcessor processor) {
        this.processor = processor;
        return this;
    }
    
    public StepFactory writer(AbstractItemWriter writer) {
        this.writer = writer;
        return this;
    }
    
    /**
     * Cria uma nova instancia da fabrica.
     *
     * @param type Tipo do step.
     * @return Fabrica.
     */
    public static StepFactory get(Step.Type type) {
        return new StepFactory(type);
    }

    /**
     * Recebe um tipo de step.
     *
     * @param type Tipo de Step.
     */
    private StepFactory(Step.Type type) {
        this.type = type;
    }

    /**
     * Recebe um tasklet.
     *
     * @param tasklet Tasklet.
     * @return Fabrica de Steps.
     */
    public StepFactory tasklet(AbstractTasklet tasklet) {
        this.tasklet = tasklet;
        return this;
    }

    /**
     * Recebe um StepParameters.
     *
     * @param parameters StepParameters.
     * @return Fabrica de Steps.
     */
    public StepFactory parameters(StepParameters parameters) {
        this.parameters = parameters;
        return this;
    }

    /**
     * Cria um objeto {@link Step}.
     *
     * @return um {@link Step}.
     */
    public Step create() {
        if (this.type.equals(Step.Type.TASKLET)) {
            return new Step(this.tasklet, this.type, this.parameters);
        } else if (this.type.equals(Step.Type.CHUNK)) {
            return new Step(this.reader, this.processor, this.writer, this.type, this.parameters, this.commitInterval);
        }
        return null;
    }

}
