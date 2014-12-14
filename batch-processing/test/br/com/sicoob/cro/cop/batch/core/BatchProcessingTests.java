/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sicoob.cro.cop.batch.core;

import java.util.Properties;
import static junit.framework.Assert.assertEquals;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

/**
 *
 * @author rogerioalves21
 */
public class BatchProcessingTests {

    private static final Log LOG = LogFactory.getLog(BatchProcessingTests.class.getName());
    
    public BatchProcessingTests() {
    }
    
    @Test
    public void exemploTasklet() {
        Properties jobParameters = new Properties();
        jobParameters.put("exemplo", "Parametro passado pelo Step");
        BatchProcess launcher = BatchApplication.createExecutionProcess("taskletExample2", jobParameters);
        BatchExecution execution = launcher.start();
        while (execution.getStatus() != Status.COMPLETED) {
            LOG.info(execution.toString());
        }
        LOG.info(execution.toString());
        assertEquals(Result.Type.SUCCESS, execution.getResult().getType());
    }

    @Test
    public void exemploArquivoTasklet() {
        Properties jobParameters = new Properties();
        jobParameters.put("nomeArquivo", "OpLm.csv");
        BatchProcess launcher = BatchApplication.createExecutionProcess("taskletExample", jobParameters);
        BatchExecution execution = launcher.start();
        while (execution.getStatus() != Status.COMPLETED) {
            LOG.info(execution.toString());
        }
        LOG.info(execution.toString());
        assertEquals(Result.Type.SUCCESS, execution.getResult().getType());
    }
    
    @Test
    public void exemploChunk() {
        Properties jobParameters = new Properties();
        jobParameters.put("nomeArquivo", "OpLm.csv");
        BatchProcess launcher = BatchApplication.createExecutionProcess("chunkExample", jobParameters);
        BatchExecution execution = launcher.start();
        while (execution.getStatus() != Status.COMPLETED) {
            LOG.info(execution.toString());
        }
        LOG.info(execution.toString());
        assertEquals(Result.Type.SUCCESS, execution.getResult().getType());
    }
}
