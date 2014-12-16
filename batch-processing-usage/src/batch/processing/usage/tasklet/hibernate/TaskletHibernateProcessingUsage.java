/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package batch.processing.usage.tasklet.hibernate;

import batch.processing.usage.tasklet.BatchProcessingUsage;
import br.com.sicoob.cro.cop.batch.core.BatchApplication;
import br.com.sicoob.cro.cop.batch.core.BatchExecution;
import br.com.sicoob.cro.cop.batch.core.BatchProcess;
import br.com.sicoob.cro.cop.batch.core.Status;
import br.com.sicoob.cro.cop.util.BatchStartException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rogerioalves21
 */
public class TaskletHibernateProcessingUsage {
    
    public static void main(String[] args) {
        new TaskletHibernateProcessingUsage().executeOpAd();
    }
    
    private void executeOpAd() {
        try {
            Properties jobParameters = new Properties();
            jobParameters.put("nomeArquivo", "OpAd.csv");
            BatchProcess launcher = BatchApplication.createExecutionProcess("taskletHibernateExample", jobParameters);
            BatchExecution execution = launcher.start();
            while (execution.getStatus() != Status.COMPLETED) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    
                }
            }
            System.out.println(execution.toString());
        } catch (BatchStartException ex) {
            Logger.getLogger(BatchProcessingUsage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
