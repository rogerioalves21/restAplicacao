/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sicoob.cro.cop.batch.core;

import br.com.sicoob.cro.cop.batch.configuration.annotation.Context;
import br.com.sicoob.cro.cop.batch.step.tasklet.AbstractTasklet;
import br.com.sicoob.cro.cop.batch.step.tasklet.TaskletContext;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author rogerioalves21
 */
public class FileReadTasklet extends AbstractTasklet {

    private static final Log LOG = LogFactory.getLog(FileReadTasklet.class.getName());

    @Context
    private TaskletContext context;

    public void execute() {
        List<Operation> operacoes = new ArrayList<>();
        InputStream source = this.getClass().getResourceAsStream(this.context.getParameters().get("nomeArquivo").toString()/*"OpLm.csv"*/);
        Scanner scan = new Scanner(source);
        while (scan.hasNext()) {
            String[] dados = scan.next().split(";");
            Operation operacao = new Operation(dados[0],
                    new BigDecimal(dados[1]), dados[2], new BigDecimal(dados[3]));
            operacoes.add(operacao);
        }

        // calcula o percentual de provisionamento
        for (Operation operacao : operacoes) {
            LOG.info(operacao.toString());
        }
    }

}
