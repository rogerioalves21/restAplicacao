/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sicoob.cro.cop.batch.core;

import br.com.sicoob.cro.cop.batch.step.StepExecution;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author rogerioalves21
 */
public class StepListenerImpl implements BatchStepListener {

    private static final Log LOG = LogFactory.getLog(StepListenerImpl.class);
    
    public void beforeStep(StepExecution stepExecution) {
        LOG.info("BEFORE STEP - " + stepExecution.getIdJob() + " " + stepExecution.getIdStep());
    }

    public void afterStep(StepExecution stepExecution) {
        LOG.info("AFTER STEP - " + stepExecution.getIdJob() + " " + stepExecution.getIdStep());
    }
    
}
