/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sicoob.cro.cop.batch.core;

/**
 *
 * @author rogerioalves21
 * @param <O> Istancia de Origem.
 * @param <R> Instanciade Retorno.
 */
public interface Processor<O, R> {

    R process(O item);

}
