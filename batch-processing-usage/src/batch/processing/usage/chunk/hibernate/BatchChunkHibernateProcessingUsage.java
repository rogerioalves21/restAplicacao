/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package batch.processing.usage.chunk.hibernate;

import br.com.sicoob.cro.cop.batch.core.BatchApplication;
import br.com.sicoob.cro.cop.batch.core.BatchExecution;
import br.com.sicoob.cro.cop.batch.core.BatchProcess;
import br.com.sicoob.cro.cop.batch.core.Status;
import java.util.Properties;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author rogerioalves21
 */
public class BatchChunkHibernateProcessingUsage {
    
    private static final Log LOG = LogFactory.getLog(BatchChunkHibernateProcessingUsage.class.getName());
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            Properties jobParameters = new Properties();
            jobParameters.put("nomeArquivo", "OpLm.csv");
            BatchProcess launcher = BatchApplication.createExecutionProcess("chunkHibernateExample", jobParameters);
            BatchExecution execution = launcher.start();
             while (execution.getStatus() != Status.COMPLETED) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    
                }
            }
            System.out.println(execution.toString());
        } catch (Exception ex) {
            LOG.error(ex);
        }
    }
    
}
