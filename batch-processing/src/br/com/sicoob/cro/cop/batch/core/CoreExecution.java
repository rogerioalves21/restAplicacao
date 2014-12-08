/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sicoob.cro.cop.batch.core;

import java.util.concurrent.ExecutorService;

/**
 * Define o comportamento de uma execucao de item.
 *
 * @author Rogerio Alves Rodrigues
 * @param <T> Parametro.
 * @param <R> Retorno.
 */
public interface CoreExecution<T, R> {

    R execute(T item) throws Exception;

    R execute(T item, ExecutorService executor) throws Exception;

}
