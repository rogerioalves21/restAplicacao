/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package batch.processing.usage.chunk;

import br.com.sicoob.cro.cop.batch.core.BatchApplication;
import br.com.sicoob.cro.cop.batch.core.BatchProcess;

/**
 *
 * @author rogerioalves21
 */
public class BatchChunkProcessingUsage {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        BatchProcess launcher = BatchApplication.createExecutionProcess(ProcessarArquivoChunkBatchConfig.class);
        launcher.start();
    }

}
