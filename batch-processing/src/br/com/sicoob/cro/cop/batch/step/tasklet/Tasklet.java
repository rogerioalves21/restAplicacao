/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sicoob.cro.cop.batch.step.tasklet;

/**
 * Um tasklet é um tipo de passo do batch que pode ser usado para qualquer tipo
 * de processamento que um chunk não possa.
 *
 * @author Rogerio Alves Rodrigues
 */
public interface Tasklet {

    /**
     *
     * Faz o processamento dos dados de um job. Se algum erro ocorrer, deverá
     * retornar a exceção para terminar o fluxo como FAIL.
     *
     * @throws Exception se ocorrer algum erro.
     */
    void process() throws Exception;

}
