/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sicoob.cro.cop.batch.step.chunk;

import br.com.sicoob.cro.cop.batch.step.Step;
import java.util.concurrent.Callable;

/**
 *
 * @author Rogerio Alves Rodrigues
 */
public interface IChunkExecutor extends Callable<Boolean> {

    void setStep(Step step);
    
    Boolean execute() throws Exception;

}
