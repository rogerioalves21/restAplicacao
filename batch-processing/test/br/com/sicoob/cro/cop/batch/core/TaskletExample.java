/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sicoob.cro.cop.batch.core;

import java.util.logging.Level;
import java.util.logging.Logger;
import static junit.framework.Assert.assertEquals;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author rogerioalves21
 */
public class TaskletExample {

    public TaskletExample() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void taskletExample() {
        BatchProcess launcher = BatchApplication.createExecutionProcess(ProcessarArquivoBatchConfig.class);
        BatchExecution execution = launcher.start();
        
        System.out.println(execution.toString());
        while (execution.getStatus() != Status.COMPLETED) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(TaskletExample.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println(execution.toString());
        }
        System.out.println(execution.toString());

        assertEquals(Result.Type.SUCCESS, execution.getResult().getType());
        
    }
}
