/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package batch.processing.usage.tasklet;

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
public class BatchProcessingUsage {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        BatchProcessingUsage usage = new BatchProcessingUsage();
        usage.executeOpAd();
        usage.executeOpLm();
    }
    
    private void executeOpLm() {
        try {
            Properties jobParameters = new Properties();
            jobParameters.put("nomeArquivo", "OpLm.csv");
            BatchProcess launcher = BatchApplication.createExecutionProcess("taskletExample", jobParameters);
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
    
    private void executeOpAd() {
        try {
            Properties jobParameters = new Properties();
            jobParameters.put("nomeArquivo", "OpAd.csv");
            BatchProcess launcher = BatchApplication.createExecutionProcess("taskletExample", jobParameters);
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
