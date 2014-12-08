/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sicoob.cro.cop.batch.step;

import br.com.sicoob.cro.cop.batch.step.tasklet.Tasklet;
import static br.com.sicoob.cro.cop.util.Validation.checkNull;

/**
 * Represnta o passo da tarefa {@link Job} que sera executado.
 *
 * Porem sua execucao estara associada a uma interface {@link Tasklet}, que
 * contera o codigo fonte para a realizacao do passo.
 *
 * @author rogerioalves21
 */
public class Step {

    private Tasklet tasklet;
    private Type type;
    private StepParameters parameters;

    /**
     * Constroi uma instancia de step.
     *
     * @param tasklet Tasklet para execucao.
     * @param type Tipo do Step.
     * @param parameters Parametros do step.
     */
    public Step(Tasklet tasklet, Type type, StepParameters parameters) {
        checkNull(tasklet, "tasklet");
        checkNull(type, "type");
        this.tasklet = tasklet;
        this.type = type;
        this.parameters = parameters;
    }

    /**
     * @return the tasklet
     */
    public Tasklet getTasklet() {
        return tasklet;
    }

    /**
     * @param tasklet the tasklet to set
     */
    public void setTasklet(Tasklet tasklet) {
        this.tasklet = tasklet;
    }

    /**
     * @return the type
     */
    public Type getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(Type type) {
        this.type = type;
    }

    /**
     * @return the parameters
     */
    public StepParameters getParameters() {
        return parameters;
    }

    /**
     * @param parameters the parameters to set
     */
    public void setParameters(StepParameters parameters) {
        this.parameters = parameters;
    }

    /**
     * Tipos de Step.
     */
    public static enum Type {

        CHUNK, TASKLET;
    }

}
