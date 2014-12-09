/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sicoob.cro.cop.batch.core;

import br.com.sicoob.cro.cop.batch.core.BatchApplication;
import br.com.sicoob.cro.cop.batch.core.IExecution;
import br.com.sicoob.cro.cop.batch.core.launcher.Launchers;
import br.com.sicoob.cro.cop.batch.core.Result;
import br.com.sicoob.cro.cop.batch.core.Status;
import java.util.logging.Level;
import java.util.logging.Logger;
import junit.framework.TestCase;

/**
 *
 * @author rogerioalves21
 */
public class ExemploUmTest extends TestCase {

    public void testUmJobUmStep() {
        BatchProcess launcher = BatchApplication.createExecutionProcess(ExemploBatchConfig.class);
        IExecution execution = launcher.start();

        System.out.println(execution.toString());
        while (execution.getStatus() == Status.RUNNING) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(ExemploUmTest.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println(execution.toString());
        }
        System.out.println(execution.toString());

        assertEquals(Result.Type.SUCCESS, execution.getResult().getType());
    }

    public void testSemTasklet() {
        BatchProcess launcher = BatchApplication.createExecutionProcess(BatchConfigSemTasklet.class);
        IExecution execution = launcher.start();

        System.out.println(execution.toString());
        while (execution.getStatus() == Status.RUNNING) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(ExemploUmTest.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println(execution.toString());
        }
        System.out.println(execution.toString());

        System.out.println(execution.getItemError().getMessage());

        assertEquals(Result.Type.FAIL, execution.getResult().getType());
    }

}
