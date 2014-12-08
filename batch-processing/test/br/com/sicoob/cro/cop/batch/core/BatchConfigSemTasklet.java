/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sicoob.cro.cop.batch.core;

import br.com.sicoob.cro.cop.batch.configuration.annotation.BatchConfiguration;
import br.com.sicoob.cro.cop.batch.configuration.annotation.JobBuilderFactory;
import br.com.sicoob.cro.cop.batch.configuration.annotation.Jobs;
import br.com.sicoob.cro.cop.batch.configuration.annotation.StepBuilderFactory;
import br.com.sicoob.cro.cop.batch.job.Job;
import br.com.sicoob.cro.cop.batch.factory.JobFactory;
import br.com.sicoob.cro.cop.batch.step.Step;
import br.com.sicoob.cro.cop.batch.factory.StepFactory;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rogerioalves21
 */
@BatchConfiguration
public class BatchConfigSemTasklet {

    @JobBuilderFactory
    private JobFactory jobFactory;

    @StepBuilderFactory(type = Step.Type.TASKLET)
    private StepFactory stepFactory;

    @Jobs
    public List<Job> getJobs() {
        List<Step> steps = new ArrayList<>();
        Step step = stepFactory
                .tasklet(null)
                .create();
        steps.add(step);
        Job job = jobFactory
                .job("job test")
                .steps(steps)
                .create();

        List<Job> jobs = new ArrayList<>();
        jobs.add(job);

        return jobs;
    }

}
