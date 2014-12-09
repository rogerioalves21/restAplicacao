/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sicoob.cro.cop.batch.core.launcher;

import br.com.sicoob.cro.cop.batch.core.BatchExecution;

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
     * @param configurationObject Objeto de configuracao do batch.
     * @return um {@link BatchExecution}.
     */
    BatchExecution run(Object configurationObject);

}
