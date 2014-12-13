/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sicoob.cro.cop.batch.core;

import br.com.sicoob.cro.cop.batch.job.Job;

/**
 *
 * @author Rogerio Alves Rodrigues
 */
public class JobDataExecution implements JobExecution {

    // job em execucao
    private final Job job;
    
    public JobDataExecution(Job job) {
        this.job = job;
    }
    
    public String getId()  {
        return this.job.getId();
    }

    public Integer getNumberOfSteps() {
        return this.job.getSteps().size();
    }

    public Job.Status getStatus() {
        return this.job.getStatus();
    }

    public Job.Mode getMode() {
        return this.job.getMode();
    }
    
}
