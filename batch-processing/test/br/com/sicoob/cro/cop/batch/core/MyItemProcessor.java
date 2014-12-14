/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sicoob.cro.cop.batch.core;

import br.com.sicoob.cro.cop.batch.configuration.AbstractItemProcessor;
import br.com.sicoob.cro.cop.batch.configuration.annotation.Context;
import br.com.sicoob.cro.cop.batch.step.chunk.ChunkContext;

/**
 *
 * @author rogerioalves21
 */
public class MyItemProcessor extends AbstractItemProcessor {
    
    @Context
    private ChunkContext context;

    @Override
    public Object processItem(Object item) {
        Operation operacao = (Operation) item;
        operacao.setCalculoProvisao(operacao.calcularProvisao());
        return operacao;
    }
    
}
