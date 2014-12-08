/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sicoob.cro.cop.batch.core;

import br.com.sicoob.cro.cop.batch.job.Job;
import br.com.sicoob.cro.cop.util.ItemError;

/**
 * Implementacao da interface {@link Execution}.
 *
 * @author Rogerio Alves Rodrigues
 */
public class DataExecution implements Execution {

    private Status status = Status.RUNNING;
    private Job runningJob;
    private Result result;
    private ItemError itemError;

    @Override
    public Status getStatus() {
        return this.status;
    }

    @Override
    public Job runningJob() {
        return this.runningJob;
    }

    @Override
    public ItemError getError() {
        return this.itemError;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     * @return the runningJob
     */
    public Job getRunningJob() {
        return runningJob;
    }

    /**
     * @param runningJob the runningJob to set
     */
    public void setRunningJob(Job runningJob) {
        this.runningJob = runningJob;
    }

    @Override
    public Result getResult() {
        return this.result;
    }

    /**
     * @param result the result to set
     */
    public void setResult(Result result) {
        this.result = result;
    }

    public void addErrorMessage(String error) {
        this.itemError = new ItemError(error);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("\n#########################");
        builder.append("\nStatus: ".concat(this.status.name()));
        builder.append("\nResult: ".concat(this.result != null ? this.result.getType().name() : "Nenhum"));
        builder.append("\nRunning Job: ".concat(this.runningJob != null ? this.runningJob.getNome() : "Nenhum"));
        builder.append("\nRunning Job Status: ".concat(this.runningJob != null ? this.runningJob.getStatus().name() : "Nenhum"));
        builder.append("\nError: ".concat(this.itemError != null ? this.itemError.getMessage() : "Nenhum"));
        return builder.toString();
    }

}
