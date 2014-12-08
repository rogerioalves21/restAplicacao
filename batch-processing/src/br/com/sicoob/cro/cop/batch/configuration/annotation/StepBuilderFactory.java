/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sicoob.cro.cop.batch.configuration.annotation;

import br.com.sicoob.cro.cop.batch.step.Step;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Injeta a dependencia para o step factory.
 *
 * @author Rogerio Alves Rodrigues
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface StepBuilderFactory {

    /**
     * Tipo de execucao de step.
     *
     * @return um {@link Step.Type}.
     */
    Step.Type type();

}
