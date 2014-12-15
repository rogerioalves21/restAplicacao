/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sicoob.cro.cop.batch.step.tasklet;

import br.com.sicoob.cro.cop.batch.core.Result;
import br.com.sicoob.cro.cop.batch.step.Step;
import br.com.sicoob.cro.cop.batch.step.StepExecutorHelper;
import org.apache.commons.logging.LogFactory;

/**
 * Classe abstrata para quando o cliente ano quiser implementar a interface
 * tasklet.
 *
 * @author Rogerio Alves Rodrigues
 */
public abstract class AbstractTasklet implements Tasklet {

    private Step step;

    public void setStep(Step step) {
        this.step = step;
    }

    /**
     * Implementa um callable de concorrencia.
     *
     * @return Não é utilizado.
     * @throws Exception para algum erro.
     */
    public Boolean call() throws Exception {
        StepExecutorHelper.beforeStep(this.step);
        Result result = Result.SUCCESS;
        try {
            process();
        } catch (Exception excecao) {
            LogFactory.getLog(AbstractTasklet.class).fatal(excecao);
            result = Result.FAIL;
        } finally {
            StepExecutorHelper.afterStep(this.step, result);
        }
        return Boolean.TRUE;
    }

    /**
     * Implementa o processamento do tasklet.
     *
     * @throws Exception para qualquer erro.
     */
    public abstract void process() throws Exception;

}
