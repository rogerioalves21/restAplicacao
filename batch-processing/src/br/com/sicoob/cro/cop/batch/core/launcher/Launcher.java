/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sicoob.cro.cop.batch.core.launcher;

/**
 * Define o comportamento de um launcher.
 *
 * Padrao Command. Apenas recebe a requisicao e executa.
 *
 * @author Rogerio Alves Rodrigues
 * @param <T> Tipo de retorno do launcher.
 */
public interface Launcher<T> {

    /**
     * Executa a logica de lancamento.
     *
     * @return um Tipo.
     */
    T run();

}
