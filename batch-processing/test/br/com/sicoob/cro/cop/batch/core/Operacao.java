/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sicoob.cro.cop.batch.core;

import java.math.BigDecimal;

/**
 *
 * @author rogerioalves21
 */
public class Operacao {
    
    private String identificador;
    private BigDecimal saldoDevedor;
    private String rating;
    private BigDecimal percRating;
    private BigDecimal percProvisao;

    public Operacao(String identificador, BigDecimal saldoDevedor, String rating, BigDecimal percRating) {
        this.identificador = identificador;
        this.saldoDevedor = saldoDevedor;
        this.rating = rating;
        this.percRating = percRating;
    }
    
    /**
     * @return the identificador
     */
    public String getIdentificador() {
        return identificador;
    }

    /**
     * @param identificador the identificador to set
     */
    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    /**
     * @return the saldoDevedor
     */
    public BigDecimal getSaldoDevedor() {
        return saldoDevedor;
    }

    /**
     * @param saldoDevedor the saldoDevedor to set
     */
    public void setSaldoDevedor(BigDecimal saldoDevedor) {
        this.saldoDevedor = saldoDevedor;
    }

    /**
     * @return the rating
     */
    public String getRating() {
        return rating;
    }

    /**
     * @param rating the rating to set
     */
    public void setRating(String rating) {
        this.rating = rating;
    }

    /**
     * @return the percProvisao
     */
    public BigDecimal getPercProvisao() {
        return percProvisao;
    }

    /**
     * @param percProvisao the percProvisao to set
     */
    public void setPercProvisao(BigDecimal percProvisao) {
        this.percProvisao = percProvisao;
    }
    
    public BigDecimal calcularProvisao() {
        return this.saldoDevedor.multiply(this.percRating.divide(new BigDecimal(100.0)));
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n####################");
        sb.append("\nOperacao: " + this.getIdentificador());
        sb.append("\nSaldo Devedor: " + this.getSaldoDevedor().toString());
        sb.append("\nRating: " + this.getRating());
        sb.append("\nPercentual de Provisao: " + this.getPercRating() + "%");
        sb.append("\nCalculo da Provisao: " + this.calcularProvisao().toString() + "%");
        sb.append("\n####################");
        return sb.toString();
    }

    /**
     * @return the percRating
     */
    public BigDecimal getPercRating() {
        return percRating;
    }

    /**
     * @param percRating the percRating to set
     */
    public void setPercRating(BigDecimal percRating) {
        this.percRating = percRating;
    }
    
}
