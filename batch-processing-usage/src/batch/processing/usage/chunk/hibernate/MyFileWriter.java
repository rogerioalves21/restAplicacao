/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package batch.processing.usage.chunk.hibernate;

import batch.processing.usage.chunk.hibernate.domain.Operation;
import batch.processing.usage.util.HibernateUtil;
import br.com.sicoob.cro.cop.batch.configuration.AbstractItemWriter;
import br.com.sicoob.cro.cop.batch.configuration.annotation.Context;
import br.com.sicoob.cro.cop.batch.step.chunk.ChunkContext;
import java.util.List;
import java.util.Random;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;

/**
 *
 * @author rogerioalves21
 */
public class MyFileWriter extends AbstractItemWriter {

    @Context
    private ChunkContext context;

    private static final Log LOG = LogFactory.getLog(MyFileWriter.class.getName());

    @Override
    public void writeItems(List<Object> items) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        System.out.println("Abrindo a transação");
        try {
            LOG.info("Writer chamado");
            for (Object operacao : items) {
                Operation op = (Operation) operacao;
                op.setCalculoProvisao(op.calcularProvisao());
                LOG.info(((Operation) operacao).toString());
                session.save(operacao);
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        System.out.println("Fechando a transação");
    }

}
