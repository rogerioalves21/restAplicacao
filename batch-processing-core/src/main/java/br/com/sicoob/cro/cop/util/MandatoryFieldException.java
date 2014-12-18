/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sicoob.cro.cop.util;

/**
 * Campo obrigatorio.
 *
 * Classe para o lancamento de erros de runtime do tipo campo obrigatorio.
 *
 * @author Rogerio Alves Rodrigues
 */
public class MandatoryFieldException extends RuntimeException {

    /**
     * Recebe uma mensagem e envia o erro.
     *
     * @param message String da mensagem.
     */
    public MandatoryFieldException(String message) {
        super(message);
    }

}
