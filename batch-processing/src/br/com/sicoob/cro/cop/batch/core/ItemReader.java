/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sicoob.cro.cop.batch.core;

import java.io.Serializable;

/**
 * Define o comportamento de um Leitor de item.
 *
 * @author Rogerio Alves Rodrigues
 */
public interface ItemReader {

    Object readItem(Serializable recordNumber) throws Exception;

}
