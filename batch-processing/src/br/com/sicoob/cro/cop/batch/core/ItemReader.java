/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sicoob.cro.cop.batch.core;

import br.com.sicoob.cro.cop.batch.step.chunk.RecordNumber;

/**
 * Define o comportamento de um Leitor de item.
 *
 * @author Rogerio Alves Rodrigues
 */
public interface ItemReader {

    /**
     * Metodo responsavel por receber um contador e obter um registro para este
     * id.
     *
     * @param recordNumber Id do Incremento.
     * @return um Objeto lido para o id.
     * @throws Exception para algum erro.
     */
    Object readItem(RecordNumber recordNumber) throws Exception;

}
