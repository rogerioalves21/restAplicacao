/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sicoob.cro.cop.batch.configuration;

import br.com.sicoob.cro.cop.batch.core.ItemReader;
import br.com.sicoob.cro.cop.batch.step.chunk.RecordNumber;

/**
 * Classe abstrata para um {@link ItemReader}.
 * @author Rogerio Alves Rodriuges
 */
public abstract class AbstractItemReader implements ItemReader {

    /**
     * Metodo responsavel por receber um contador e obter um registro para este
     * id.
     *
     * @param recordNumber Id do Incremento.
     * @return um Objeto lido para o id.
     * @throws Exception para algum erro.
     */
    public abstract Object readItem(RecordNumber recordNumber) throws Exception;
    
}
