/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sicoob.cro.cop.batch.configuration;

import br.com.sicoob.cro.cop.batch.core.ItemWriter;
import java.util.List;

/**
 * Implementa um {@link ItemWriter}.
 *
 * @author Rogerio Alves Rodrigues
 */
public abstract class AbstractItemWriter implements ItemWriter {

    public abstract void writeItems(List<Object> items);

}
