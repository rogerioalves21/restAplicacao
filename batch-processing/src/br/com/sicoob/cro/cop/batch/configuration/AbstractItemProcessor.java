/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sicoob.cro.cop.batch.configuration;

import br.com.sicoob.cro.cop.batch.core.ItemProcessor;

/**
 * Implementa um {@link ItemProcessor}.
 *
 * @author Rogerio Alves Rodrigues
 */
public abstract class AbstractItemProcessor implements ItemProcessor {

    public abstract Object processItem(Object item);
    
}
