/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sicoob.cro.cop.batch.step;

import java.util.HashMap;
import java.util.Map;

/**
 * Contem os parametros que serao passados para o step.
 *
 * @author Rogerio Alves Rodrigues
 */
public class StepParameters {

    // parametros
    private final Map<String, Object> parameters = new HashMap<>();

    /**
     * Adiciona um parametro.
     *
     * @param key Chave.
     * @param value Valor.
     */
    public void add(String key, Object value) {
        this.parameters.put(key, value);
    }

    /**
     * Retorna o parametro.
     *
     * @param key Chave.
     * @return valor do parametro.
     */
    public Object get(String key) {
        return this.parameters.get(key);
    }

}
