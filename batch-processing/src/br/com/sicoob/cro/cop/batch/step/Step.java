/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sicoob.cro.cop.batch.step;

import br.com.sicoob.cro.cop.batch.configuration.AbstractItemProcessor;
import br.com.sicoob.cro.cop.batch.configuration.AbstractItemReader;
import br.com.sicoob.cro.cop.batch.configuration.AbstractItemWriter;
import br.com.sicoob.cro.cop.batch.core.BatchStepListener;
import br.com.sicoob.cro.cop.batch.job.Job;
import br.com.sicoob.cro.cop.batch.step.tasklet.AbstractTasklet;
import br.com.sicoob.cro.cop.batch.step.tasklet.Tasklet;
import br.com.sicoob.cro.cop.util.BatchKeys;
import br.com.sicoob.cro.cop.util.BatchPropertiesUtil;
import static br.com.sicoob.cro.cop.util.Validation.checkNull;
import static br.com.sicoob.cro.cop.util.Validation.getOr;

/**
 * Represnta o passo da tarefa {@link Job} que sera executado.
 *
 * Porem sua execucao estara associada a uma interface {@link Tasklet}, que
 * contera o codigo fonte para a realizacao do passo.
 *
 * @author rogerioalves21
 */
public class Step {

    private String id;
    private AbstractTasklet tasklet;
    private AbstractItemReader reader;
    private AbstractItemProcessor processor;
    private AbstractItemWriter writer;
    private Type type;
    private StepParameters parameters;
    private Integer commitInterval;
    private Job job;
    private BatchStepListener listener;

    /**
     * Constroi uma instancia de step.
     *
     * @param reader ItemReader.
     * @param processor ItemProcessor.
     * @param writer ItemWriter.
     * @param type Tipo de Step.
     * @param parameters Parametros do step.
     * @param commitInterval Intervalo para commit.
     */
    public Step(AbstractItemReader reader, AbstractItemProcessor processor,
            AbstractItemWriter writer, Type type, StepParameters parameters,
            Integer commitInterval) {
        this.reader = reader;
        this.processor = processor;
        this.writer = writer;
        this.type = type;
        this.parameters = parameters;
        this.commitInterval = commitInterval;
    }

    /**
     * Constroi uma instancia de step.
     *
     * @param tasklet Tasklet para execucao.
     * @param type Tipo do Step.
     * @param parameters Parametros do step.
     */
    public Step(AbstractTasklet tasklet, Type type, StepParameters parameters) {
        checkNull(tasklet, BatchPropertiesUtil.getInstance().getMessage(
                BatchKeys.MANDATORY_FIELD.getKey(), BatchKeys.TASKLET.getKey()));
        checkNull(type, BatchPropertiesUtil.getInstance().getMessage(
                BatchKeys.MANDATORY_FIELD.getKey(), BatchKeys.TYPE.getKey()));
        this.tasklet = tasklet;
        this.type = type;
        this.parameters = parameters;
    }

    /**
     * @return the tasklet
     */
    public AbstractTasklet getTasklet() {
        return tasklet;
    }

    /**
     * @param tasklet the tasklet to set
     */
    public void setTasklet(AbstractTasklet tasklet) {
        this.tasklet = tasklet;
    }

    /**
     * @return the type
     */
    public Type getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(Type type) {
        this.type = type;
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

    /**
     * @return the reader
     */
    public AbstractItemReader getReader() {
        return reader;
    }

    /**
     * @param reader the reader to set
     */
    public void setReader(AbstractItemReader reader) {
        this.reader = reader;
    }

    /**
     * @return the processor
     */
    public AbstractItemProcessor getProcessor() {
        return processor;
    }

    /**
     * @param processor the processor to set
     */
    public void setProcessor(AbstractItemProcessor processor) {
        this.processor = processor;
    }

    /**
     * @return the writer
     */
    public AbstractItemWriter getWriter() {
        return writer;
    }

    /**
     * @param writer the writer to set
     */
    public void setWriter(AbstractItemWriter writer) {
        this.writer = writer;
    }

    /**
     * @return the commitInterval
     */
    public Integer getCommitInterval() {
        return getOr(this.commitInterval,
                Integer.valueOf(BatchPropertiesUtil.getInstance().getMessage(
                                BatchKeys.COMMIT_INTERVAL_DEFAULT.getKey())));
    }

    /**
     * @param commitInterval the commitInterval to set
     */
    public void setCommitInterval(Integer commitInterval) {
        this.commitInterval = commitInterval;
    }

    /**
     * @return the job
     */
    public Job getJob() {
        return job;
    }

    /**
     * @param job the job to set
     */
    public void setJob(Job job) {
        this.job = job;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        checkNull(id, BatchPropertiesUtil.getInstance().getMessage(
                BatchKeys.MANDATORY_FIELD.getKey(), BatchKeys.ID.getKey()));
        this.id = id;
    }

    /**
     * @return the listener
     */
    public BatchStepListener getListener() {
        return listener;
    }

    /**
     * @param listener the listener to set
     */
    public void setListener(BatchStepListener listener) {
        this.listener = listener;
    }

    /**
     * Tipos de Step.
     */
    public static enum Type {

        CHUNK, TASKLET;
    }

}
