/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package batch.processing.usage.chunk.hibernate;

import br.com.sicoob.cro.cop.batch.core.BatchStepListener;
import br.com.sicoob.cro.cop.batch.step.BatchStepContribution;

/**
 *
 * @author rogerioalves21
 */
public class ChunkHibernateStepListener implements BatchStepListener {
    
    public void beforeStep(BatchStepContribution stepExecution) {
        System.out.println("BEFORE STEP - " + stepExecution.getIdJob() + " " + stepExecution.getIdStep());
        //Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        //session.beginTransaction();
        //System.out.println("Abrindo a transação");
    }

    public void afterStep(BatchStepContribution stepExecution) {
        System.out.println("AFTER STEP - " + stepExecution.getIdJob() + " " + stepExecution.getIdStep());
        //Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        //session.getTransaction().commit();
        //System.out.println("Fechando a transação");
    }
    
}
