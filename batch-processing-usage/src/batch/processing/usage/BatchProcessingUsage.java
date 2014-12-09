/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package batch.processing.usage;

import br.com.sicoob.cro.cop.batch.core.BatchApplication;
import br.com.sicoob.cro.cop.batch.core.IExecution;
import br.com.sicoob.cro.cop.batch.core.Status;
import br.com.sicoob.cro.cop.batch.core.BatchProcess;
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
        BatchProcess launcher = BatchApplication.createExecutionProcess(ProcessarArquivoBatchConfig.class);
        IExecution execution = launcher.start();
        
        System.out.println(execution.toString());
        while (execution.getStatus() != Status.COMPLETED) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(BatchProcessingUsage.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println(execution.toString());
        }
        System.out.println(execution.toString());
    }
    
}
