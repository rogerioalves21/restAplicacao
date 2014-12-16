/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package batch.processing.usage.tasklet.hibernate;

import batch.processing.usage.chunk.hibernate.domain.Operation;
import batch.processing.usage.util.HibernateUtil;
import br.com.sicoob.cro.cop.batch.configuration.annotation.Context;
import br.com.sicoob.cro.cop.batch.step.tasklet.AbstractTasklet;
import br.com.sicoob.cro.cop.batch.step.tasklet.TaskletContext;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import org.hibernate.Session;

/**
 *
 * @author rogerioalves21
 */
public class HibernateTasklet extends AbstractTasklet {

    @Context
    private TaskletContext context;
    
    @Override
    public void process() throws Exception {
        List<Operation> operacoes = new ArrayList<>();
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        InputStream source = this.getClass().getResourceAsStream(this.context.getParameters().get("nomeArquivo").toString());
        try (Scanner scan = new Scanner(source)) {
            while (scan.hasNext()) {
                String[] dados = scan.next().split(";");
                Operation operacao = new Operation(dados[0],
                        new BigDecimal(dados[1]), dados[2], new BigDecimal(dados[3]));
                operacoes.add(operacao);
                Random random = new Random();
                random.nextInt();
                operacao.setId(random.nextInt());
            }
            
            // calcula o percentual de provisionamento
            for (Operation operacao : operacoes) {
                session.save(operacao);
            }
        }
    }
    
}
