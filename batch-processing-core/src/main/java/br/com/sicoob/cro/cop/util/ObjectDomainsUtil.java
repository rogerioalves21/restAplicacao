/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sicoob.cro.cop.util;

/**
 * Objeto para null safe.
 *
 * @author Rogerio Alves Rodrigues
 */
public final class ObjectDomainsUtil {

    /**
     * Obtem uma instancia null safe para um determinado objeto.
     *
     * @param <T> Tipo a ser retornado.
     * @param actual Objeto atual.
     * @param safe Objeto null safe.
     * @return Instancia salva.
     */
    public static <T> T nullSafe(T actual, T safe) {
        return actual != null ? actual : safe;
    }

}
