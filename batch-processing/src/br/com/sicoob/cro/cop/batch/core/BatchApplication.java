/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sicoob.cro.cop.batch.core;

import br.com.sicoob.cro.cop.batch.configuration.annotation.BatchConfiguration;
import br.com.sicoob.cro.cop.batch.core.launcher.BatchProcess;
import br.com.sicoob.cro.cop.batch.factory.Launchers;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A porta de entrada para o processamento-batch. Cria um {@link DataExecution}
 * a partir de uma {@link BatchConfiguration}.
 *
 * <p>
 * BatchApplication suporta a criacao de um contexto de execucao baseado em jobs
 * e steps para a execucao de processamento sincrono e assincrono. Retorna a
 * objeto {@link BatchProcess} para disponibilizar de forma dinamica o
 * funcionamento do processamento. Exemplo:
 * </p>
 *
 * <pre>
 *  public void algumMetodo() {
 *      BatchProcess launcher = BatchApplication.createExecutionProcess(AlgumaConfiguracao.class);
 *      Execution execution = launcher.run();
 *      try {
 *          Thread.sleep(500);
 *      } catch (InterruptedException ex) {
 *          Logger.getLogger(AlgumaClasseTest.class.getName()).log(Level.SEVERE, null, ex);
 *      }
 *      System.out.println(execution.toString());
 *  }
 * </pre>
 *
 * @author Rogerio Alves Rodrigues
 */
public final class BatchApplication {

    /**
     * Construtor privado.
     */
    private BatchApplication() {
    }

    /**
     * Cria um contexto de execucao para o objeto de configuracao dado.
     *
     * @param <T> Tipo do objeto de configuracao.
     * @param type Objeto com os dados de configuracao.
     * @return um {@link BatchProcess}.
     */
    public static <T> BatchProcess createExecutionProcess(Class<T> type) {
        return Launchers.get(createNewInstance(type)).create();
    }

    /**
     * Obtem uma nova instancia para o tipo dado.
     *
     * @param <T> Tipo.
     * @param type Objeto.
     * @return uma nova instancia.
     */
    private static <T> Object createNewInstance(Class<T> type) {
        try {
            return type.newInstance();
        } catch (InstantiationException ex) {
            Logger.getLogger(BatchApplication.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(BatchApplication.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex);
        }
    }

}
