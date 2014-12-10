/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sicoob.cro.cop.batch.step;

import br.com.sicoob.cro.cop.batch.core.IStepExecutor;
import br.com.sicoob.cro.cop.batch.core.Result;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.FutureTask;

/**
 * Classe responsavel por executar Steps do tipo Chunk.
 *
 * @author Rogerio Alves Rodrigues
 */
public class StepChunkExecutor implements IStepExecutor {

    private final Step step;
    private final ExecutorService service;

    /**
     * Constri um StepExecutor.
     *
     * @param step Step a ser executado.
     * @param service Servico de execucao.
     */
    public StepChunkExecutor(Step step, ExecutorService service) {
        this.step = step;
        this.service = service;
    }

    public void start() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public FutureTask<Result> getResult() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
