/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sicoob.cro.cop.batch.core.launcher;

import br.com.sicoob.cro.cop.batch.core.BatchExecution;
import br.com.sicoob.cro.cop.batch.job.Job;

/**
 * Define o comportamento de um launcher.
 *
 * Padrao Command. Apenas recebe a requisicao e executa.
 *
 * @author Rogerio Alves Rodrigues
 */
public interface Launcher {

    /**
     * Executa a logica de lancamento.
     *
     * @param job Objeto job a ser executado.
     * @return um {@link BatchExecution}.
     */
    BatchExecution run(Job job);

}
