/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sicoob.cro.cop.batch.core;

import java.util.List;

/**
 * Define o comportamento de um gravador de items.
 *
 * @author Rogerio Alves Rodrigues
 */
public interface ItemWriter {

    void writeItems(List<Object> items);

}
