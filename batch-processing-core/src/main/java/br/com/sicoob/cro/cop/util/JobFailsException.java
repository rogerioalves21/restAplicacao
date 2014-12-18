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
public class JobFailsException extends Exception {
    
    public JobFailsException(String message) {
        super(message);
    }
    
    public JobFailsException(Throwable excecao) {
        super(excecao);
    }
    
    public JobFailsException(String message, Throwable excecao) {
        super(message, excecao);
    }
    
}
