/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sicoob.cro.cop.batch.core;

import br.com.sicoob.cro.cop.batch.core.launcher.Launchers;
import br.com.sicoob.cro.cop.util.BatchXmlReader;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.beanutils.ConstructorUtils;

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
     * Cria uma nova instancia de um Batch Process e iniciar sua primeira
     * execução, que é executada assincronamente.
     *
     * O {@code jobXmlName}, é o nome do arquivo XML que descreve o Job. O mesmo
     * deverá estar na pasta META-INF/batch-jobs. O nome do arquivo deverá ser o
     * mesmo do {@code jobXmlName} acrescido do .xml.
     *
     * @param jobXMLName especifica o nome do Job XML.
     * @param jobParameters especifica os atributos que serão passados para a
     * execução do job.
     * @return Retorna um BatchProcess que dará os dados do job em execução.
     */
    public static BatchProcess createExecutionProcess(String jobXMLName, Properties jobParameters) {
        // carregar o arquivo em memoria.
        BatchXmlReader reader = new BatchXmlReader(jobXMLName, jobParameters);
        reader.loadFile();
        return Launchers.get(reader.getJob()).create();
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
            return ConstructorUtils.invokeConstructor(type, new Object[0]);
        } catch (Exception ex) {
            Logger.getLogger(BatchApplication.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex);
        }
    }

}
