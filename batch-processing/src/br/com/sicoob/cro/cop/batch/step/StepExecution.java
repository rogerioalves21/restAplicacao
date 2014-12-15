/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sicoob.cro.cop.batch.step;

import br.com.sicoob.cro.cop.batch.core.Result;
import br.com.sicoob.cro.cop.batch.core.Status;

/**
 *
 * @author rogerioalves21
 */
public class StepExecution {
    
    private Status status;
    private Result result;
    private String idStep;
    private String idJob;
    private StepParameters parameters;
    
    public StepExecution(Status status, String idStep, String idJob, StepParameters parameters) {
        this.status = status;
        this.idStep = idStep;
        this.idJob = idJob;
        this.parameters = parameters;
    }

    /**
     * @return the status
     */
    public Status getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     * @return the result
     */
    public Result getResult() {
        return result;
    }

    /**
     * @param result the result to set
     */
    public void setResult(Result result) {
        this.result = result;
    }

    /**
     * @return the idStep
     */
    public String getIdStep() {
        return idStep;
    }

    /**
     * @param idStep the idStep to set
     */
    public void setIdStep(String idStep) {
        this.idStep = idStep;
    }

    /**
     * @return the idJob
     */
    public String getIdJob() {
        return idJob;
    }

    /**
     * @param idJob the idJob to set
     */
    public void setIdJob(String idJob) {
        this.idJob = idJob;
    }

    /**
     * @return the parameters
     */
    public StepParameters getParameters() {
        return parameters;
    }

    /**
     * @param parameters the parameters to set
     */
    public void setParameters(StepParameters parameters) {
        this.parameters = parameters;
    }
    
    
    
}
