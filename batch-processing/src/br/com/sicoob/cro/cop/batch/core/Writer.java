/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sicoob.cro.cop.batch.core;

/**
 *
 * @author rogerioalves21
 */
public interface Writer<T> {
    
    void wriet(T item);
    
}
