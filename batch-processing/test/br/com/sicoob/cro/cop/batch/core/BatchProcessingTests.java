/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sicoob.cro.cop.batch.core;

import br.com.sicoob.cro.cop.util.BatchStartException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import static junit.framework.Assert.assertEquals;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
import org.junit.Test;

/**
 * Testes para as execucoes de steps.
 *
 * @author Rogerio Alves Rodrigues
 */
public class BatchProcessingTests {

    private static final Log LOG = LogFactory.getLog(BatchProcessingTests.class.getName());

    public BatchProcessingTests() {
    }

    @Test
    public void withNoFileName() {
        try {
            BatchApplication.createExecutionProcess("fileNotFound", new Properties());
        } catch (BatchStartException excecao) {
            LOG.error(excecao);
            assertEquals("br.com.sicoob.cro.cop.util.BatchStartException: Nenhum arquivo com o nome fileNotFound foi encontrado.", excecao.getMessage());
        }
    }

    @Test
    public void taskletExample() {
        try {
            Properties jobParameters = new Properties();
            jobParameters.put("nomeArquivo", "OpLm.csv");
            BatchProcess launcher = BatchApplication.createExecutionProcess("taskletExample", jobParameters);
            BatchExecution execution = launcher.start();
            while (execution.getStatus() != Status.COMPLETED) {
                Thread.sleep(5000);
                LOG.info(execution.toString());
            }
            LOG.info(execution.toString());
            assertEquals(Result.Type.SUCCESS, execution.getResult().getType());
        } catch (BatchStartException excecao) {
            Assert.fail(excecao.getMessage());
        } catch (InterruptedException ex) {
            Logger.getLogger(BatchProcessingTests.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void chunkExample() {
        try {
            Properties jobParameters = new Properties();
            jobParameters.put("nomeArquivo", "OpLm.csv");
            BatchProcess launcher = BatchApplication.createExecutionProcess("chunkExample", jobParameters);
            BatchExecution execution = launcher.start();
            while (execution.getStatus() != Status.COMPLETED) {
                 Thread.sleep(5000);
                LOG.info(execution.toString());
            }
            LOG.info(execution.toString());
            assertEquals(Result.Type.SUCCESS, execution.getResult().getType());
        } catch (BatchStartException excecao) {
            Assert.fail(excecao.getMessage());
        } catch (InterruptedException ex) {
            Logger.getLogger(BatchProcessingTests.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
