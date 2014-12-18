/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sicoob.cro.cop.batch.job;

import br.com.sicoob.cro.cop.batch.core.BatchStepListener;
import br.com.sicoob.cro.cop.batch.step.Step;
import br.com.sicoob.cro.cop.util.BatchKeys;
import br.com.sicoob.cro.cop.util.BatchPropertiesUtil;
import br.com.sicoob.cro.cop.util.Validation;
import static br.com.sicoob.cro.cop.util.Validation.checkNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Classe que representa uma tarefa a ser executada.
 *
 * Contem seu nome e uma lista de {@link Step} steps.
 *
 * @author rogerioalves21
 */
public class Job {

    private String id;
    private List<Step> steps;
    private List<BatchStepListener> listeners;
    private Mode mode;
    private Status status = Status.TO_PROCESS;
    private long startTime;
    private long endTime;

    public Job(String id, Mode mode) {
        checkNull(id, BatchPropertiesUtil.getInstance().getMessage(
                BatchKeys.MANDATORY_FIELD.getKey(), BatchKeys.ID.getKey()));
        checkNull(mode, BatchPropertiesUtil.getInstance().getMessage(
                BatchKeys.MANDATORY_FIELD.getKey(), BatchKeys.MODE.getKey()));
        this.id = id;
        this.mode = mode;
    }

    /**
     * Constroi um Job.
     *
     * @param id id do Job.
     * @param steps Lista de Steps do Job.
     * @param mode Modo de execucao do Job.
     */
    public Job(String id, List<Step> steps, Mode mode) {
        checkNull(id, BatchPropertiesUtil.getInstance().getMessage(
                BatchKeys.MANDATORY_FIELD.getKey(), BatchKeys.ID.getKey()));
        checkNull(steps, BatchPropertiesUtil.getInstance().getMessage(
                BatchKeys.MANDATORY_FIELD.getKey(), BatchKeys.STEPS.getKey()));
        checkNull(mode, BatchPropertiesUtil.getInstance().getMessage(
                BatchKeys.MANDATORY_FIELD.getKey(), BatchKeys.MODE.getKey()));
        this.id = id;
        this.steps = steps;
        this.mode = mode;
    }

    /**
     * @return the nome
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the nome to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the steps
     */
    public List<Step> getSteps() {
        return steps;
    }

    /**
     * @param steps the steps to set
     */
    public void setSteps(List<Step> steps) {
        checkNull(steps, BatchPropertiesUtil.getInstance().getMessage(
                BatchKeys.MANDATORY_FIELD.getKey(), BatchKeys.STEPS.getKey()));
        this.steps = steps;
    }

    /**
     * @return the mode
     */
    public Mode getMode() {
        return mode;
    }

    /**
     * @param mode the mode to set
     */
    public void setMode(Mode mode) {
        this.mode = mode;
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
     * @return the listeners
     */
    public List<BatchStepListener> getListeners() {
        return listeners;
    }

    /**
     * @param listeners the listeners to set
     */
    public void setListeners(List<BatchStepListener> listeners) {
        this.listeners = listeners;
    }

    /**
     * Adiciona um listener ao job.
     *
     * @param listener Listener.
     */
    public void addListener(BatchStepListener listener) {
        if (Validation.isNull(this.listeners)) {
            this.listeners = new ArrayList();
        }
        this.listeners.add(listener);
    }

    /**
     * @return the startTime
     */
    public long getStartTime() {
        return startTime;
    }

    /**
     * @param startTime the startTime to set
     */
    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    /**
     * @return the endTime
     */
    public long getEndTime() {
        return endTime;
    }

    /**
     * @param endTime the endTime to set
     */
    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    /**
     * Modos do Step.
     */
    public static enum Mode {

        SYNC, ASYNC;
    }

    /**
     * Status do JOB.
     */
    public static enum Status {

        TO_PROCESS, RUNNING, FAIL, FINISHED
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(BatchPropertiesUtil.getInstance().getMessage(BatchKeys.JOB_ID.getKey(), getId()));
        sb.append(BatchPropertiesUtil.getInstance().getMessage(BatchKeys.JOB_LENGHT.getKey(), getSteps().size() + ""));
        sb.append(BatchPropertiesUtil.getInstance().getMessage(BatchKeys.JOB_MODE.getKey(), getMode().name()));
        return sb.toString();
    }

}
