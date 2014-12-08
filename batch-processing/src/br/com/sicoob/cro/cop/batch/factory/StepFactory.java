/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sicoob.cro.cop.batch.factory;

import br.com.sicoob.cro.cop.batch.step.Step;
import br.com.sicoob.cro.cop.batch.step.StepParameters;
import br.com.sicoob.cro.cop.batch.step.tasklet.Tasklet;

/**
 * Fabrica de Steps.
 *
 * Responsavel pela criacao de passos de uma tarefa.
 *
 * @author Rogerio Alves Rodrigues
 */
public final class StepFactory implements Factory<Step> {

    private Tasklet tasklet;
    private final Step.Type type;
    private StepParameters parameters;

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
    public StepFactory tasklet(Tasklet tasklet) {
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
    @Override
    public Step create() {
        return new Step(this.tasklet, this.type, this.parameters);
    }

}
