/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sicoob.cro.cop.batch.core.launcher;

import br.com.sicoob.cro.cop.batch.configuration.annotation.Inject;
import br.com.sicoob.cro.cop.batch.core.Execution;

/**
 * Classe que sera retornada para o cliente.
 *
 * @author Rogerio Alves Rodrigues
 */
public class BatchProcess {

    // Classe executora
    @Inject
    private Launcher<Execution> execution;
    // instancia vazia
    public static final BatchProcess EMPTY = new BatchProcess();

    /**
     * Executa o processamento.
     *
     * @return um {@link Execution}.
     */
    public Execution run() {
        return this.execution.run();
    }

}
