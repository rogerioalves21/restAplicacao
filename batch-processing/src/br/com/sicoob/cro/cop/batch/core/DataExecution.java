/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sicoob.cro.cop.batch.core;

import br.com.sicoob.cro.cop.util.ItemError;
import br.com.sicoob.cro.cop.util.Validation;
import java.util.Date;

/**
 * Implementacao da interface {@link BatchExecution}.
 *
 * @author Rogerio Alves Rodrigues
 */
public class DataExecution implements BatchExecution {

    private Status status = Status.STARTING;
    private JobExecution job;
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
     * @return the job
     */
    public JobExecution getJob() {
        return job;
    }

    /**
     * @param job the job to set
     */
    public void setJob(JobExecution job) {
        this.job = job;
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
        builder.append("\nJob: ".concat(this.job != null ? this.job.getId() : "Nenhum"));
        builder.append("\nJob Status: ".concat(this.job != null ? this.job.getStatus().name() : "Nenhum"));
        builder.append("\nJob start time: ".concat(this.job != null ? new Date(this.job.getStartTime()).toString() : "Nenhum"));
        builder.append("\nJob end time: ".concat(this.job != null ? new Date(this.job.getEndTime()).toString() : "Nenhum"));
        builder.append("\nError: ".concat(this.itemError != null ? this.itemError.getMessage() : "Nenhum"));
        return builder.toString();
    }

}
