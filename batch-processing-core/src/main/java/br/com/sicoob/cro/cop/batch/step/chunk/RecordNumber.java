/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sicoob.cro.cop.batch.step.chunk;

import java.io.Serializable;

/**
 * Claase reponsabel por disponbilizar a chave para a classe reader utilizar
 * como incrementador.
 *
 * @author Rogerio Alves Rodrigues
 */
public class RecordNumber implements Serializable {

    private Integer id;
    
    protected RecordNumber(Integer id) {
        this.id = id;
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    protected void setId(Integer id) {
        this.id = id;
    }
    
    protected Integer next() {
        return this.id + 1;
    }
    
}
