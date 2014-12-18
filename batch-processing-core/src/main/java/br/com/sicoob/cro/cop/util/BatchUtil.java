/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sicoob.cro.cop.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 *
 * @author rogerioalves21
 */
public final class BatchUtil {
    
    public static <T> Method[] getDeclaredMethods(T item) {
        return item.getClass().getDeclaredMethods();
    }
    
    public static <T> Field[] getDeclaredFields(T item) {
        return item.getClass().getDeclaredFields();
    }
    
}
