/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sicoob.cro.cop.batch.step.tasklet;

/**
 * Classe abstrata para quando o cliente ano quiser implementar a interface
 * tasklet.
 *
 * @author Rogerio Alves Rodrigues
 */
public abstract class AbstractTasklet implements Tasklet {

    /**
     * Implementa o processamento do tasklet.
     *
     * @throws Exception para qualquer erro.
     */
    public abstract void process() throws Exception;

}
