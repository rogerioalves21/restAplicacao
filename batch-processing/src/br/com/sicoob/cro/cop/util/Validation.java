/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sicoob.cro.cop.util;

import br.com.sicoob.cro.cop.batch.configuration.annotation.BatchConfiguration;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Classe responsavel por validacoes que o sistema necessitar ter.
 *
 * Valida os itens necessarios para o funcionamento do processamento batch.
 *
 * @author Rogerio Alves Rodrigues
 */
public final class Validation {

    /**
     * Construtor.
     */
    private Validation() {

    }

    public static Boolean notNull(Object object) {
        return object != null;
    }

    public static Boolean isNull(Object object) {
        return object == null;
    }

    public static <T> T getOr(T object, T defaultObject) {
        return notNull(object) ? object : defaultObject;
    }
    
    public static Boolean isAnnotatedWith(Object clazz, Class<? extends Annotation> annotation) {
        return clazz.getClass().isAnnotationPresent(annotation);
    }
    
    public static Boolean isFieldAnnotatedWith(Field field, Class<? extends Annotation> annotation) {
        return field.isAnnotationPresent(annotation);
    }
    
    public static Boolean isMethodAnnotatedWith(Method method, Class<? extends Annotation> annotation) {
        return method.isAnnotationPresent(annotation);
    }

    /**
     * Valida se o objeto comparavel {@code comparable} nao é nulo e verifica se
     * o {@code compareWith} é igual ao {@code comparable}.
     *
     * @param compareWith Objeto para comparacao.
     * @param comparable Object comparavel.
     * @return verdadeiro se atender aos dois requisitos.
     */
    public static Boolean notNullAndSameAs(Integer compareWith, Integer comparable) {
        return notNull(comparable) && sameAs(compareWith, comparable);
    }

    public static Boolean sameAs(Integer compareWith, Integer comparable) {
        return compareWith.compareTo(comparable) == 0;
    }

    /**
     * Checa se o objeto esta nulo e envia um erro caso esteja.
     *
     * @param object Item a ser verificado.
     * @param message Mensagem de aviso.
     * @throws br.com.sicoob.cro.cop.util.MandatoryFieldException ERro.
     */
    public static void checkNull(Object object, String message) throws MandatoryFieldException {
        if (object == null) {
            throw new MandatoryFieldException("the field " + message + " is mandatory");
        }
    }

}
