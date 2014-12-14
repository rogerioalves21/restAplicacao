/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sicoob.cro.cop.batch.core;

import br.com.sicoob.cro.cop.batch.configuration.AbstractItemReader;
import br.com.sicoob.cro.cop.batch.configuration.annotation.Context;
import br.com.sicoob.cro.cop.batch.step.chunk.ChunkContext;
import br.com.sicoob.cro.cop.batch.step.chunk.RecordNumber;
import br.com.sicoob.cro.cop.util.Validation;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Scanner;

/**
 *
 * @author rogerioalves21
 */
public class MyItemReader extends AbstractItemReader {

    @Context
    private ChunkContext context;

    public Object readItem(RecordNumber recordNumber) throws Exception {
        InputStream source = this.getClass().getResourceAsStream(this.context.getParameters().get("nomeArquivo").toString());
        Operation operacao;
        try (Scanner scan = new Scanner(source)) {
            Integer contador = 1;
            operacao = null;
            while (scan.hasNext()) {
                if (Validation.sameAs(contador, recordNumber.getId())) {
                    String[] dados = scan.next().split(";");
                    operacao = new Operation(dados[0],
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
