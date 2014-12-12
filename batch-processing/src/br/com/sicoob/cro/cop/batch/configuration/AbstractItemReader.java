/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sicoob.cro.cop.batch.configuration;

import br.com.sicoob.cro.cop.batch.core.ItemReader;
import java.io.Serializable;

/**
 * Classe abstrata para um {@link ItemReader}.
 * @author Rogerio Alves Rodriuges
 */
public abstract class AbstractItemReader implements ItemReader {

    public abstract Object readItem(Serializable recordNumber) throws Exception;
    
}
