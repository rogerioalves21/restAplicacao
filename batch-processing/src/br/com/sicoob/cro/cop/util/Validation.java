/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sicoob.cro.cop.util;

/**
 * Classe responsavel por validacoes que o sistema necessitar ter.
 *
 * Valida os itens necessarios para o funcionamento do processamento batch.
 *
 * @author Rogerio Alves Rodrigues
 */
public final class Validation {

    /**
     * Construtor.
     */
    private Validation() {

    }

    /**
     * Checa se o objeto esta nulo e envia um erro caso esteja.
     *
     * @param item Item a ser verificado.
     * @param message Mensagem de aviso.
     * @throws br.com.sicoob.cro.cop.util.MandatoryFieldException ERro.
     */
    public static void checkNull(Object item, String message) throws MandatoryFieldException {
        if (item == null) {
            throw new MandatoryFieldException("the field " + message + " is mandatory");
        }
    }

}
