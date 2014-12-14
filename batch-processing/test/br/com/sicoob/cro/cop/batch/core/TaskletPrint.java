/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sicoob.cro.cop.batch.core;

import br.com.sicoob.cro.cop.batch.configuration.annotation.Context;
import br.com.sicoob.cro.cop.batch.step.tasklet.AbstractTasklet;
import br.com.sicoob.cro.cop.batch.step.tasklet.TaskletContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author rogerioalves21
 */
public class TaskletPrint extends AbstractTasklet {

    private static final Log LOG = LogFactory.getLog(TaskletPrint.class.getName());
    
    @Context
    private TaskletContext context;
    
    public void execute() {
        LOG.info("Tasklet");
        LOG.info(context.getParameters().get("exemplo").toString());
    }

}
