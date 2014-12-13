/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package batch.processing.usage.tasklet;

import br.com.sicoob.cro.cop.batch.configuration.annotation.BatchConfiguration;
import br.com.sicoob.cro.cop.batch.configuration.annotation.JobBuilderFactory;
import br.com.sicoob.cro.cop.batch.configuration.annotation.Jobs;
import br.com.sicoob.cro.cop.batch.configuration.annotation.StepBuilderFactory;
import br.com.sicoob.cro.cop.batch.job.Job;
import br.com.sicoob.cro.cop.batch.step.Step;
import br.com.sicoob.cro.cop.batch.factory.JobFactory;
import br.com.sicoob.cro.cop.batch.factory.StepFactory;
import br.com.sicoob.cro.cop.batch.step.StepParameters;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rogerioalves21
 */
@BatchConfiguration
public class ProcessarArquivoBatchConfig {

    @JobBuilderFactory
    private JobFactory jobFactory;

    @StepBuilderFactory(type = Step.Type.TASKLET)
    private StepFactory stepFactory;

    @Jobs
    public List<Job> getJobsAD() {
        ProcessarArquivoTasklet taskLm = new ProcessarArquivoTasklet();
        ProcessarArquivoTasklet taskLm2 = new ProcessarArquivoTasklet();

        StepParameters param1 = new StepParameters();
        param1.add("nomeArquivo", "OpLm.csv");
        StepParameters param2 = new StepParameters();
        param2.add("nomeArquivo", "OpAd.csv");
        List<Step> steps = new ArrayList<>();

        Step stepAd = stepFactory
                .tasklet(taskLm2)
                .parameters(param2)
                .create();

        Step stepLm = stepFactory
                .tasklet(taskLm)
                .parameters(param1)
                .create();

        steps.add(stepAd);
        steps.add(stepLm);

        Job job = jobFactory
                .id("PROCESSA 2 STEPS - LEITURA DE ARQUIVOS")
                .mode(Job.Mode.ASYNC)
                .steps(steps)
                .create();

        List<Job> jobs = new ArrayList<>();
        jobs.add(job);

        return jobs;
    }
    
    @Jobs
    public List<Job> getJobsLM() {
        ProcessarArquivoTasklet taskLm = new ProcessarArquivoTasklet();
        List<Step> steps = new ArrayList<>();
        StepParameters param = new StepParameters();
        param.add("nomeArquivo", "OpLm.csv");

        Step stepLm = stepFactory
                .tasklet(taskLm)
                .parameters(param)
                .create();

        steps.add(stepLm);

        Job job = jobFactory
                .id("PROCESSA 1 STEP - LE UM ARQUIVO")
                .mode(Job.Mode.ASYNC)
                .steps(steps)
                .create();

        List<Job> jobs = new ArrayList<>();
        jobs.add(job);

        return jobs;
    }

}
