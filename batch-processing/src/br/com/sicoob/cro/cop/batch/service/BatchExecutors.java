/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sicoob.cro.cop.batch.service;

import java.util.concurrent.Executors;

/**
 *
 * @author rogerioalves21
 */
public final class BatchExecutors {
    
    public static BatchExecutorService newSingleThreadExecutor() {
        return new ExecutorThreadPool(Executors.newSingleThreadExecutor());
    }
    
    public static BatchExecutorService newFixedThreadPool(int nThreads) {
        return new ExecutorThreadPool(Executors.newFixedThreadPool(nThreads));
    }
    
}
