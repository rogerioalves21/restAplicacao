/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sicoob.cro.cop.batch.core;

import java.util.concurrent.FutureTask;

/**
 * Define o comportamento de uma execucao de item.
 *
 * @author Rogerio Alves Rodrigues
 */
public interface IStepExecutor {

    /**
     * Executa um step.
     *
     * @throws Exception para qualquer erro.
     */
    void start() throws Exception;

    /**
     * Obtem o resultado da execucao do step.
     *
     * @return um {@link FutureTask}.
     */
    FutureTask<Result> getResult();
}
