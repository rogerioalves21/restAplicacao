/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cro.cop.batch.service;

import java.util.concurrent.ExecutorService;

/**
 *
 * @author Rogerio Alves Rodrigues
 */
public class ExecutorThreadPool implements BatchExecutorService {

    private final ExecutorService executorService;
    
    public ExecutorThreadPool(ExecutorService executorService) {
        this.executorService = executorService;
    }
    
    public void shutdown() {
        this.executorService.shutdown();
    }

    public void execute(Runnable command) {
        this.executorService.execute(command);
    }
    
}
