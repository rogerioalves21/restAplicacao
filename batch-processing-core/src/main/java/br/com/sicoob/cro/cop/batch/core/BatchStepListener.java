/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sicoob.cro.cop.batch.core;

import br.com.sicoob.cro.cop.batch.step.BatchStepContribution;

/**
 * Define o comportamento para os listener de execucao.
 *
 * @author Rogerio Alves Rodrigues
 */
public interface BatchStepListener {

    /**
     * Será acessado antes do inicio do processo.
     *
     * @param stepExecution Dados do step a ser executado.
     */
    void beforeStep(BatchStepContribution stepExecution);

    /**
     * Será acessado quando o processo estiver terminado.
     *
     * @param stepExecution Dados do step que foi executado.
     */
    void afterStep(BatchStepContribution stepExecution);

}
