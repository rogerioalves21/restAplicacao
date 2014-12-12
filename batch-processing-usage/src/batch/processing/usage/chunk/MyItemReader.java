/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package batch.processing.usage.chunk;

import batch.processing.usage.tasklet.OperacaoUsage;
import br.com.sicoob.cro.cop.batch.configuration.AbstractItemReader;
import br.com.sicoob.cro.cop.batch.configuration.annotation.Context;
import br.com.sicoob.cro.cop.batch.step.chunk.ChunkContext;
import java.io.InputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Scanner;

/**
 *
 * @author rogerioalves21
 */
public class MyItemReader extends AbstractItemReader {

    @Context
    private ChunkContext context;

    @Override
    public Object readItem(Serializable recordNumber) throws Exception {
        InputStream source = this.getClass().getResourceAsStream(this.context.getParameters().get("nomeArquivo").toString());
        OperacaoUsage operacao;
        try (Scanner scan = new Scanner(source)) {
            Integer contador = 0;
            Integer rn = (Integer) recordNumber;
            operacao = null;
            while (scan.hasNext()) {
                if (contador.compareTo(rn) == 0) {
                    String[] dados = scan.next().split(";");
                    operacao = new OperacaoUsage(dados[0],
                            new BigDecimal(dados[1]), dados[2], new BigDecimal(dados[3]));
                    break;
                } else {
                    scan.next();
                }
                contador++;
            }
        }
        return operacao;
    }

}
