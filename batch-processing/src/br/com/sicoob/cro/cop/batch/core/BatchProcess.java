/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sicoob.cro.cop.batch.core;

import br.com.sicoob.cro.cop.batch.core.launcher.Launcher;
import com.google.inject.Inject;

/**
 * Classe que sera retornada para o cliente.
 *
 * @author Rogerio Alves Rodrigues
 */
public class BatchProcess {

    // Classe executora
    private final Launcher execution;
    // Objeto de configuracao do batch
    private Object configurationObject;

    /**
     * Construtor.
     *
     * @param execution Classe o lancamento da execucao.
     */
    @Inject
    public BatchProcess(Launcher execution) {
        this.execution = execution;
    }

    /**
     * Adiciona o objeto de configuracao do batch.
     *
     * @param configurationObject Objeto de configuracao.
     */
    public void addConfigurationObject(Object configurationObject) {
        this.configurationObject = configurationObject;
    }

    /**
     * Executa o processamento.
     *
     * @return um {@link BatchExecution}.
     */
    public BatchExecution start() {
        return this.execution.run(this.configurationObject);
    }

}
