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
        BatchExecution execution = launcher.start();
        
        while (execution.getStatus() != Status.COMPLETED) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                
            }
            
        }
        System.out.println(execution.toString());
    }

}
