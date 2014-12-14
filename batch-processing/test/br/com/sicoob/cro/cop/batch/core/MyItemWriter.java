/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sicoob.cro.cop.batch.core;

import br.com.sicoob.cro.cop.batch.configuration.AbstractItemWriter;
import br.com.sicoob.cro.cop.batch.configuration.annotation.Context;
import br.com.sicoob.cro.cop.batch.step.chunk.ChunkContext;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author rogerioalves21
 */
public class MyItemWriter extends AbstractItemWriter {

    @Context
    private ChunkContext context;
    
    private static final Log LOG = LogFactory.getLog(MyItemWriter.class.getName());
    
    @Override
    public void writeItems(List<Object> items) {
        LOG.info("Writer chamado");
        for (Object operacao : items) {
            LOG.info(((Operation)operacao).toString());
        }
    }
    
}
