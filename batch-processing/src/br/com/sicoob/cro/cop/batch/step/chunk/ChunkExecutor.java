/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sicoob.cro.cop.batch.step.chunk;

import br.com.sicoob.cro.cop.batch.configuration.ItemProcessorInjector;
import br.com.sicoob.cro.cop.batch.configuration.ItemReaderInjector;
import br.com.sicoob.cro.cop.batch.configuration.ItemWriterInjector;
import br.com.sicoob.cro.cop.batch.core.Result;
import br.com.sicoob.cro.cop.batch.core.Status;
import br.com.sicoob.cro.cop.batch.step.Step;
import br.com.sicoob.cro.cop.batch.step.StepExecution;
import br.com.sicoob.cro.cop.batch.step.StepExecutorHelper;
import br.com.sicoob.cro.cop.util.Validation;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.beanutils.ConstructorUtils;
import org.apache.commons.logging.LogFactory;

/**
 * Esta classe eh reponsavel por executar no estilo chunk o processo.
 *
 * @author Rogerio Alves Rodrigues
 */
public class ChunkExecutor implements IChunkExecutor {

    private Step step;
    private List<Object> objectsToWrite;
    private Integer commitIntervalCounter = 0;

    /**
     * Construtor.
     */
    public ChunkExecutor() {

    }

    public void setStep(Step step) {
        this.step = step;
    }

    private Step getStep() {
        return this.step;
    }

    /**
     * Chama o processo terceirizado.
     *
     * @return o resultado do processo.
     * @throws Exception ao ocorrer algum erro.
     */
    public Boolean execute() throws Exception {
        return this.call();

    }

    /**
     * Adiciona um item ja processado para a lista de writable.
     *
     * @param item Objeto processado.
     */
    private void addWritable(Object item) {
        if (Validation.isNull(this.objectsToWrite)) {
            this.objectsToWrite = new ArrayList();
        }
        this.objectsToWrite.add(item);
    }

    /**
     * Executa o ItemReader, ItemProcessor e ItemWriter
     *
     * @return um Resultado.
     * @throws Exception ao ocorrer algum erro
     */
    public Boolean call() throws Exception {
        Result result = Result.SUCCESS;
        injectDependencies();
        StepExecutorHelper.beforeStep(this.step);
        try {
            runChunkProcess();
            callWriter();
        } catch (Exception excecao) {
            LogFactory.getLog(ChunkExecutor.class).fatal(excecao);
            result = Result.FAIL;
        } finally {
            StepExecutorHelper.afterStep(this.step, result);
        }
        return Boolean.TRUE;
    }

    private void notifyBeforeStep() {
        if (Validation.notNull(this.step.getListener())) {
            StepExecution stepExecution = new StepExecution(Status.STARTED,
                    this.step.getId(), this.step.getJob().getId(),
                    this.step.getParameters());
            this.step.getListener().beforeStep(stepExecution);
        }
    }

    private void notifyAfterStep() {
        if (Validation.notNull(this.step.getListener())) {
            StepExecution stepExecution = new StepExecution(Status.STARTED,
                    this.step.getId(), this.step.getJob().getId(),
                    this.step.getParameters());
            this.step.getListener().beforeStep(stepExecution);
        }
    }

    /**
     * Faz o processo de execucao do modo chunk.
     *
     * @throws Exception para algum erro.
     */
    private void runChunkProcess() throws Exception {
        RecordNumber recordNumber = new RecordNumber(1);
        this.commitIntervalCounter = 0;
        Object item = getStep().getReader().readItem(recordNumber);
        if (Validation.notNull(item)) {
            addWritable(getStep().getProcessor().processItem(item));
        }
        Boolean keepRunning = Validation.notNull(item);
        while (keepRunning) {
            this.commitIntervalCounter++;
            recordNumber.setId(recordNumber.next());
            Object itemId = getStep().getReader().readItem(recordNumber);
            keepRunning = Validation.notNull(itemId);
            if (keepRunning) {
                addWritable(getStep().getProcessor().processItem(itemId));
            }
            checkCommitInterval(this.commitIntervalCounter);
        }
    }

    /**
     * Verifica se o intervalo de commit foi alcancado, se sim chama o writer e
     * zera o contado de commits novamente.
     *
     * @param commitIntervalCounter Contador de itens para commit.
     */
    private void checkCommitInterval(Integer commitIntervalCounter) {
        if (Validation.notNullAndSameAs(commitIntervalCounter, getStep().getCommitInterval())) {
            callWriter();
            this.commitIntervalCounter = 0;
        }
    }

    /**
     * Chama o writer e passa a lista para a escrita.
     */
    private void callWriter() {
        getStep().getWriter().writeItems(this.objectsToWrite);
        this.objectsToWrite.clear();
    }

    /**
     * Injeta as dependencias do reader, processor e writer.
     *
     * @throws Exception para algum erro.
     */
    private void injectDependencies() throws Exception {
        ConstructorUtils.invokeConstructor(ItemReaderInjector.class, getStep()).inject();
        ConstructorUtils.invokeConstructor(ItemProcessorInjector.class, getStep()).inject();
        ConstructorUtils.invokeConstructor(ItemWriterInjector.class, getStep()).inject();
    }

}
