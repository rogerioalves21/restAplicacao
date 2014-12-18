/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sicoob.cro.cop.batch.core;

/**
 * Define o comporamento de um processamento de item.
 *
 * @author Rogerio Alves Rodrigues
 *
 */
public interface ItemProcessor {

    Object processItem(Object item);

}
