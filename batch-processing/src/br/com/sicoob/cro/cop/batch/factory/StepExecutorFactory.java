/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sicoob.cro.cop.batch.factory;

import br.com.sicoob.cro.cop.batch.configuration.BatchProcessModule;
import br.com.sicoob.cro.cop.batch.service.BatchExecutorService;
import br.com.sicoob.cro.cop.batch.core.IStepExecutor;
import br.com.sicoob.cro.cop.batch.step.Step;
import br.com.sicoob.cro.cop.batch.step.StepChunkExecutor;
import br.com.sicoob.cro.cop.batch.step.StepTaskletExecutor;
import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 *
 * @author rogerioalves21
 */
public class StepExecutorFactory implements Factory<IStepExecutor> {

    private Step step;
    private BatchExecutorService service;
    
    public StepExecutorFactory() {
        
    }
    
    public StepExecutorFactory and(BatchExecutorService service) {
        this.service = service;
        return this;
    }
    
    public StepExecutorFactory of(Step step) {
        this.step = step;
        return this;
    }
    
    public IStepExecutor create() {
        IStepExecutor stepExecutor = null;
        if (this.step.getType().equals(Step.Type.TASKLET)) {
            return new StepTaskletExecutor(this.step, this.service);
        } else if (this.step.getType().equals(Step.Type.CHUNK)) {
            Injector injector = Guice.createInjector(new BatchProcessModule());
            IStepExecutor chunkExecutor = new StepChunkExecutor(this.step, this.service);
            injector.injectMembers(chunkExecutor);
            return chunkExecutor;
        }
        return stepExecutor;
    }
    
}
