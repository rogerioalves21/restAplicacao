/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sicoob.cro.cop.batch.core;

import br.com.sicoob.cro.cop.batch.job.Job;
import br.com.sicoob.cro.cop.util.ItemError;
import java.util.List;

/**
 * Define o comportamento dos dados de uma execucao.
 *
 * @author Rogerio Alves Rodrigues
 */
public interface BatchExecution {

    /**
     * Retorna o status do processamento.
     *
     * @return o status do processamento.
     */
    Status getStatus();

    /**
     * Obtem o resultado do processamento.
     *
     * @return um {@link Result}.
     */
    Result getResult();

    /**
     * Retorna o job que esta rodando.
     *
     * @return um {@link Job},
     */
    JobExecution getRunningJob();

    /**
     * Retorna todos os erros que ocorreram na execucao.
     *
     * @return uma lista {@link List}.
     */
    ItemError getItemError();
}
