/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sicoob.cro.cop.batch.core;

/**
 * Objeto que sera o resultado de steps, jobs e execucoes.
 *
 * @author Rogerio Alves Rodrigues
 */
public final class Result {

    private Type type = Type.SUCCESS;
    public static final Result SUCCESS = new Result(Type.SUCCESS);
    public static final Result FAIL = new Result(Type.FAIL);

    /**
     * Construtor privado.
     *
     * @param type Tipo do resultado.
     */
    private Result(Type type) {
        this.type = type;
    }

    /**
     * @return the tipo
     */
    public Type getType() {
        return type;
    }

    /**
     * Tipo do resultado.
     */
    public static enum Type {

        SUCCESS, FAIL;
    }

}
