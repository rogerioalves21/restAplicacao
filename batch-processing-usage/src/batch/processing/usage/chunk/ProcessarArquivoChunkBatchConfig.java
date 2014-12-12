/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package batch.processing.usage.chunk;

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
public class ProcessarArquivoChunkBatchConfig {

    @JobBuilderFactory
    private JobFactory jobFactory;

    @StepBuilderFactory(type = Step.Type.CHUNK)
    private StepFactory stepFactory;

    @Jobs
    public List<Job> getJobsChunkStyle() {
        MyItemReader myReader = new MyItemReader();
        MyItemProcessor myProcessor = new MyItemProcessor();
        MyItemWriter myWriter = new MyItemWriter();

        StepParameters params = new StepParameters();
        params.add("nomeArquivo", "OpLm.csv");
        List<Step> steps = new ArrayList<>();

        Step step = stepFactory
                .reader(myReader)
                .processor(myProcessor)
                .writer(myWriter)
                .parameters(params)
                .create();

        steps.add(step);

        Job job = jobFactory
                .job("PROCESSA ESTILO CHUNK - LEITURA DE ARQUIVOS")
                .mode(Job.Mode.ASYNC)
                .steps(steps)
                .create();

        List<Job> jobs;
        jobs = new ArrayList<>();
        jobs.add(job);

        return jobs;
    }
    
}
