/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sicoob.cro.cop.batch.core;

import br.com.sicoob.cro.cop.batch.job.Job;

/**
 * Define o comportamento de uma execucao de item.
 *
 * @author Rogerio Alves Rodrigues
 */
public interface IJobExecutor {

    /**
     * Inicia a execucao do job.
     *
     * @throws Exception para qualquer erro.
     */
    void start() throws Exception;

    /**
     * Retorna o resultado do job.
     *
     * @return
     */
    Job.Status getStatus();

    /**
     * Verifica se o job falhou.
     *
     * @return se o job falhou.
     */
    Boolean fails();
    
    /**
     * Metodo responsavel por notificar os listener.Ï
     */
    void notifyListeners();

}
