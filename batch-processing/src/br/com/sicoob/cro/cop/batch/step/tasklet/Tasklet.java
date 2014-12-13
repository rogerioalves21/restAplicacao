/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sicoob.cro.cop.batch.step.tasklet;

import java.util.concurrent.Callable;

/**
 * Define o comportamento de uma tarefa simples;
 *
 * Contem um metodo que executa e retorna o resultado da execucao.
 *
 * @author Rogerio Alves Rodrigues
 */
public interface Tasklet extends Callable<Boolean> {
    
}
