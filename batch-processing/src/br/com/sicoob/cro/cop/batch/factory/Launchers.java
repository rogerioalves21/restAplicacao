/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sicoob.cro.cop.batch.factory;

import br.com.sicoob.cro.cop.batch.configuration.LauncherInjector;
import br.com.sicoob.cro.cop.batch.core.launcher.BatchProcess;
import br.com.sicoob.cro.cop.util.ObjectDomainsUtil;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Metodos utilitarios para trabalhar com launchers.
 *
 * @author Rogerio Alves Rodrigues
 */
public class Launchers implements Factory<BatchProcess> {

    // configuracao do batch
    private final Object configuration;

    /**
     * Construtor privado.
     *
     * @param Object Configuracao do batch.
     */
    private Launchers(Object configuration) {
        this.configuration = configuration;
    }

    /**
     * Retorna uma nota instancia do launcher factory.
     *
     * @param configuration Configuracao do batch.
     * @return um {@link Launchers}.
     */
    public static Launchers get(Object configuration) {
        return new Launchers(configuration);
    }

    /**
     * Cria um Laucnher.
     *
     * @return um {@link BatchProcess}.
     */
    @Override
    public BatchProcess create() {
        BatchProcess process = new BatchProcess();
        try {
            new LauncherInjector(process, this.configuration).inject();
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(Launchers.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Launchers.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex);
        }
        return ObjectDomainsUtil.nullSafe(process, BatchProcess.EMPTY);
    }

}
