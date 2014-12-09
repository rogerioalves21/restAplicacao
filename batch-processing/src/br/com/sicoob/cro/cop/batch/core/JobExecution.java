/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sicoob.cro.cop.batch.core;

import br.com.sicoob.cro.cop.batch.job.Job;

/**
 *
 * Dados do Job em Execucao.
 *
 * @author Rogerio Alves Rodrigues
 */
public interface JobExecution {

    /**
     *
     * @return o nome do job.
     */
    String getName();

    /**
     *
     * @return o numero de steps do job.
     */
    Integer getNumberOfSteps();

    /**
     *
     * @return O status do job.
     */
    Job.Status getStatus();

    /**
     *
     * @return O modo de job.
     */
    Job.Mode getMode();

}
