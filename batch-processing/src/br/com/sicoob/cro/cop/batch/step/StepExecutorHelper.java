/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sicoob.cro.cop.batch.step;

import br.com.sicoob.cro.cop.batch.core.Result;
import br.com.sicoob.cro.cop.batch.core.Status;
import br.com.sicoob.cro.cop.util.Validation;

/**
 *
 * @author rogerioalves21
 */
public final class StepExecutorHelper {
    
    public static void afterStep(Step step, Result result) {
        if (Validation.notNull(step.getListener())) {
            BatchStepContribution stepExecution = new BatchStepContribution(Status.COMPLETED,
                    step.getId(), step.getJob().getId(),
                    step.getParameters());
            stepExecution.setResult(result);
            step.getListener().afterStep(stepExecution);
        }
    }

    public static void beforeStep(Step step) {
        if (Validation.notNull(step.getListener())) {
            BatchStepContribution stepExecution = new BatchStepContribution(Status.STARTED,
                    step.getId(), step.getJob().getId(),
                    step.getParameters());
            step.getListener().beforeStep(stepExecution);
        }
    }
    
}
