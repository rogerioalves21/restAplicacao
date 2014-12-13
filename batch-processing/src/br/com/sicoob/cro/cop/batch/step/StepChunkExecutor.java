/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sicoob.cro.cop.batch.step;

import br.com.sicoob.cro.cop.batch.service.BatchExecutorService;
import br.com.sicoob.cro.cop.batch.core.IStepExecutor;
import br.com.sicoob.cro.cop.batch.step.chunk.IChunkExecutor;
import com.google.inject.Inject;
import java.util.concurrent.FutureTask;

/**
 * Classe responsavel por executar Steps do tipo Chunk.
 *
 * @author Rogerio Alves Rodrigues
 */
public class StepChunkExecutor implements IStepExecutor {

    private final Step step;
    private final BatchExecutorService service;
    private FutureTask<Boolean> task;
    @Inject
    private IChunkExecutor chunkExecutor;

    /**
     * Constri um StepExecutor.
     *
     * @param step Step a ser executado.
     * @param service Servico de execucao.
     */
    public StepChunkExecutor(Step step, BatchExecutorService service) {
        this.step = step;
        this.service = service;
    }

    public void start() throws Exception {
        this.chunkExecutor.setStep(this.step);
        this.task = new FutureTask(this.chunkExecutor);
        this.service.execute(task);
    }

    public FutureTask<Boolean> getResult() {
        return this.task;
    }

}
