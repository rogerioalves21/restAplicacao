/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sicoob.cro.cop.batch.core;

import br.com.sicoob.cro.cop.util.ItemError;
import br.com.sicoob.cro.cop.util.Validation;

/**
 * Implementacao da interface {@link BatchExecution}.
 *
 * @author Rogerio Alves Rodrigues
 */
public class DataExecution implements BatchExecution {

    private Status status = Status.STARTING;
    private JobExecution runningJob;
    private Result result;
    private ItemError itemError;

    public Status getStatus() {
        return this.status;
    }

    public ItemError getItemError() {
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
    public JobExecution getRunningJob() {
        return runningJob;
    }

    /**
     * @param runningJob the runningJob to set
     */
    public void setRunningJob(JobExecution runningJob) {
        this.runningJob = runningJob;
    }

    public Result getResult() {
        return this.result;
    }

    /**
     * @param result the result to set
     */
    public void setResult(Result result) {
        this.result = result;
    }

    public void addErrorMessage(Throwable excecao) {
        this.itemError = new ItemError(Validation.getOr(excecao.getCause(), excecao).getMessage());
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("\n#########################");
        builder.append("\nStatus: ".concat(this.status.name()));
        builder.append("\nResult: ".concat(this.result != null ? this.result.getType().name() : "Nenhum"));
        builder.append("\nRunning Job: ".concat(this.runningJob != null ? this.runningJob.getId() : "Nenhum"));
        builder.append("\nRunning Job Status: ".concat(this.runningJob != null ? this.runningJob.getStatus().name() : "Nenhum"));
        builder.append("\nError: ".concat(this.itemError != null ? this.itemError.getMessage() : "Nenhum"));
        return builder.toString();
    }

}
