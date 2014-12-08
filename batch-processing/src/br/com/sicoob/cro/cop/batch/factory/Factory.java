/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sicoob.cro.cop.batch.factory;

/**
 * Define o comportamento do padrao factory.
 * 
 * @author Rogerio Alves Rodrigues
 */
public interface Factory<T> {
    
    T create();
    
}
