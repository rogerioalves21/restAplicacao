/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package batch.processing.usage.chunk;

import batch.processing.usage.tasklet.OperacaoUsage;
import br.com.sicoob.cro.cop.batch.configuration.AbstractItemWriter;
import br.com.sicoob.cro.cop.batch.configuration.annotation.Context;
import br.com.sicoob.cro.cop.batch.step.chunk.ChunkContext;
import java.util.List;

/**
 *
 * @author rogerioalves21
 */
public class MyItemWriter extends AbstractItemWriter {

    @Context
    private ChunkContext context;
    
    @Override
    public void writeItems(List<Object> items) {
        for (Object operacao : items) {
            System.out.println(((OperacaoUsage)operacao).toString());
        }
    }
    
}
