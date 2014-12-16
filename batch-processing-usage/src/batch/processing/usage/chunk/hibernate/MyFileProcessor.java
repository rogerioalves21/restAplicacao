/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package batch.processing.usage.chunk.hibernate;

import batch.processing.usage.chunk.hibernate.domain.Operation;
import br.com.sicoob.cro.cop.batch.configuration.AbstractItemProcessor;
import br.com.sicoob.cro.cop.batch.configuration.annotation.Context;
import br.com.sicoob.cro.cop.batch.step.chunk.ChunkContext;
import java.util.Random;

/**
 *
 * @author rogerioalves21
 */
public class MyFileProcessor extends AbstractItemProcessor {

    @Context
    private ChunkContext context;

    @Override
    public Object processItem(Object item) {
        Operation operacao = (Operation) item;
        Random random = new Random();
        random.nextInt();
        operacao.setId(random.nextInt());
        operacao.setCalculoProvisao(operacao.calcularProvisao());
        return operacao;
    }

}
