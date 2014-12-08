/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sicoob.cro.cop.batch.factory;

import br.com.sicoob.cro.cop.batch.job.Job;
import br.com.sicoob.cro.cop.batch.step.Step;
import java.util.List;

/**
 * Fabrica de jobs.
 *
 * Responsavel pela criacao de jobs.
 *
 * @author Rogerio Alves Rodrigues
 */
public final class JobFactory implements Factory<Job> {

    private String nome;
    private List<Step> steps;
    private Job.Mode mode;

    /**
     * Retorna uma nova instancia da fabrica.
     *
     * @return um {@link JobFactory}.
     */
    public static JobFactory get() {
        return new JobFactory();
    }

    /**
     * Construtor.
     */
    private JobFactory() {

    }

    /**
     * Recebe o nome do Job.
     *
     * @param nome Nome do Job.
     * @return um {@link JobFactory}.
     */
    public JobFactory job(String nome) {
        this.nome = nome;
        return this;
    }

    /**
     * Modo do Job.
     *
     * @param mode Modo.
     * @return um {@link JobFactory}.
     */
    public JobFactory mode(Job.Mode mode) {
        this.mode = mode;
        return this;
    }

    /**
     * Recebe uma lista de steps.
     *
     * @param steps Lista de Steps.
     * @return um {@link JobFactory}.
     */
    public JobFactory steps(List<Step> steps) {
        this.steps = steps;
        return this;
    }

    /**
     * Fabrica um objeto Job.
     *
     * @return um {@link Job},
     */
    @Override
    public Job create() {
        return new Job(this.nome, this.steps, this.mode);
    }

}
