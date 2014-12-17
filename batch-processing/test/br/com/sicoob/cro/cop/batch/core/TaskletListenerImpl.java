/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sicoob.cro.cop.batch.core;

import br.com.sicoob.cro.cop.batch.step.BatchStepContribution;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author rogerioalves21
 */
public class TaskletListenerImpl implements BatchStepListener {

    public void beforeStep(BatchStepContribution stepExecution) {
        LogFactory.getLog(TaskletListenerImpl.class).info("BEFORE - STEP - TASKLET");
    }

    public void afterStep(BatchStepContribution stepExecution) {
      LogFactory.getLog(TaskletListenerImpl.class).info("AFTER - STEP - TASKLET");
    }
    
}
