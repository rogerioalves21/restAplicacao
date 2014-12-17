/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sicoob.cro.cop.batch.step.tasklet;

import br.com.sicoob.cro.cop.batch.core.BatchCallable;
import br.com.sicoob.cro.cop.batch.core.Result;
import br.com.sicoob.cro.cop.batch.job.Job;
import br.com.sicoob.cro.cop.batch.step.Step;
import br.com.sicoob.cro.cop.batch.step.StepExecutorHelper;
import br.com.sicoob.cro.cop.util.BatchKeys;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author rogerioalves21
 */
public class TaskletExecutor implements BatchCallable {

    private static final Log LOG = LogFactory.getLog(TaskletExecutor.class);
    private final Step step;

    public TaskletExecutor(Step step) {
        this.step = step;
    }

    public Boolean call() throws Exception {
        Result result = Result.SUCCESS;
        StepExecutorHelper.beforeStep(this.step);
        try {
            execute();
        } catch (Exception excecao) {
            result = Result.FAIL;
            LOG.error(excecao);
            this.step.getJob().setStatus(Job.Status.FAIL);
        } finally {
            StepExecutorHelper.afterStep(this.step, result);
        }
        return Boolean.TRUE;
    }

    private void execute() throws Exception {
        this.step.getTasklet().process();
    }

}
