/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sicoob.cro.cop.util;

/**
 *
 * @author rogerioalves21
 */
public class BatchStartException extends Exception {
    
    public BatchStartException(String message) {
        super(message);
    }
    
    public BatchStartException(Throwable excecao) {
        super(excecao);
    }
    
}
