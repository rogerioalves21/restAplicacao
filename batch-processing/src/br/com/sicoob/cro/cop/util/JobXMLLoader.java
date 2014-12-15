/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sicoob.cro.cop.util;

import br.com.sicoob.cro.cop.batch.configuration.AbstractItemProcessor;
import br.com.sicoob.cro.cop.batch.configuration.AbstractItemReader;
import br.com.sicoob.cro.cop.batch.configuration.AbstractItemWriter;
import br.com.sicoob.cro.cop.batch.core.BatchStepListener;
import br.com.sicoob.cro.cop.batch.job.Job;
import br.com.sicoob.cro.cop.batch.step.Step;
import br.com.sicoob.cro.cop.batch.step.StepParameters;
import br.com.sicoob.cro.cop.batch.step.tasklet.AbstractTasklet;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

/**
 * Classe responsavel por ler um arquivo xml contendo os dados do processamento
 * e retorna uma classe {@link Job}.
 *
 * @author Rogerio Alves Rodrigues
 */
public class JobXMLLoader {

    private static final Log LOG = LogFactory.getLog(JobXMLLoader.class.getName());
    private final String jobXMlName;
    private final Properties jobParameters;
    public static final String PREFIX = "META-INF/batch-jobs/";
    private Job job;
    private static final String ID = "id";
    private static final String STEP = "step";
    private static final String CHUNK = "chunk";
    private static final String READER = "reader";
    private static final String PROCESSOR = "processor";
    private static final String WRITER = "writer";
    private static final String TASKLET = "tasklet";
    private static final String LISTENER = "listener";
    private static final String XML_SUFFIX = ".xml";
    private static final String COMMIT_INTERVAL = "commit-interval";
    private static final String REF = "ref";
    private static final String FILENOTFOUND = "xml.reader.filenotfound";

    public JobXMLLoader(String jobXMlName, Properties jobParameters) {
        this.jobXMlName = jobXMlName;
        this.jobParameters = jobParameters;
    }

    /**
     * Le o arquivo {@code jobXMLName} e cria os objetos para o processamento.
     *
     * @throws BatchStartException para algum erro de leitura e inicializacao.
     */
    public void loadJSL() throws BatchStartException {
        try {
            ClassLoader tccl = Thread.currentThread().getContextClassLoader();
            InputStream inputStream = tccl.getResourceAsStream(
                    PREFIX.concat(this.jobXMlName.concat(XML_SUFFIX)));

            if (Validation.isNull(inputStream)) {
                throw new BatchStartException(BatchPropertiesUtil.getInstance().getMessage(FILENOTFOUND, this.jobXMlName));
            }

            SAXBuilder builder = new SAXBuilder();
            Document document = (Document) builder.build(inputStream);
            Element rootNode = document.getRootElement();

            this.job = new Job(rootNode.getAttributeValue(ID), Job.Mode.ASYNC);
            loadSteps(rootNode);
        } catch (Exception excecao) {
            LOG.fatal(excecao);
            throw new BatchStartException(excecao);
        }
    }

    /**
     * Le o XML e cria o objeto de Step.
     *
     * @param rootNode Nodo princiapl
     * @throws Exception para algum erro.
     */
    private void loadSteps(Element rootNode) throws Exception {
        List steps = rootNode.getChildren(STEP);
        List<Step> jobSteps = new ArrayList();
        for (Object obj : steps) {
            Element stepNode = (Element) obj;
            StepParameters stepParameters = parseParameters();
            Element taskletNode = stepNode.getChild(TASKLET);
            Element chunkNode = stepNode.getChild(CHUNK);
            if (Validation.notNull(taskletNode)) {
                Step step = new Step(getTasklet(taskletNode), Step.Type.TASKLET, stepParameters);
                step.setId(stepNode.getAttributeValue(ID));
                getListener(stepNode, step);
                jobSteps.add(step);
            } else if (Validation.notNull(chunkNode)) {
                Step step = new Step(getReader(chunkNode), getProcessor(chunkNode),
                        getWriter(chunkNode), Step.Type.CHUNK, stepParameters,
                        getCommitInterval(chunkNode));
                step.setId(stepNode.getAttributeValue(ID));
                getListener(stepNode, step);
                jobSteps.add(step);
            }
        }
        this.job.setSteps(jobSteps);
    }

    private void getListener(Element stepNode, Step step) throws Exception {
        Element listenerNode = stepNode.getChild(LISTENER);
        if (Validation.notNull(listenerNode)) {
            step.setListener((BatchStepListener) Class.forName(listenerNode.getAttributeValue(REF)).newInstance());
        }
    }

    /**
     *
     * @return o job que foi criado.
     */
    public Job getJob() {
        return this.job;
    }

    /**
     * Obtem o Objeto Reader do XML.
     *
     * @param chunk Elemento que contem os dados para o reader.
     * @return um {@link AbstractItemReader}.
     * @throws Exception para algum erro.
     */
    private AbstractItemReader getReader(Element chunk) throws Exception {
        return (AbstractItemReader) Class.forName(chunk.getAttributeValue(READER)).newInstance();
    }

    /**
     * Obtem o Objeto Processor do XML.
     *
     * @param chunk Elemento que contem os dados para o processor.
     * @return um {@link AbstractItemProcessor}.
     * @throws Exception para algum erro.
     */
    private AbstractItemProcessor getProcessor(Element chunk) throws Exception {
        return (AbstractItemProcessor) Class.forName(chunk.getAttributeValue(PROCESSOR)).newInstance();
    }

    /**
     * Obtem o Objeto Writer do XML.
     *
     * @param chunk Elemento que contem os dados para o writer.
     * @return um {@link AbstractItemWriter}.
     * @throws Exception para algum erro.
     */
    private AbstractItemWriter getWriter(Element chunk) throws Exception {
        return (AbstractItemWriter) Class.forName(chunk.getAttributeValue(WRITER)).newInstance();
    }

    /**
     * Obtem o Objeto Integer do XML.
     *
     * @param chunk Elemento que contem os dados para o intervalo de commit.
     * @return um {@link Integer}.
     * @throws Exception para algum erro.
     */
    private Integer getCommitInterval(Element chunk) throws Exception {
        return Integer.valueOf(chunk.getAttributeValue(COMMIT_INTERVAL));
    }

    private StepParameters parseParameters() {
        StepParameters parameters = new StepParameters();
        if (Validation.notNull(this.jobParameters)) {
            for (Map.Entry<Object, Object> key : this.jobParameters.entrySet()) {
                parameters.add((String) key.getKey(), key.getValue());
            }
        }
        return parameters;
    }

    /**
     * Obtem o Objeto Tasklet do XML.
     *
     * @param tasklet Elemento que contem os dados para o tasklet.
     * @return um {@link AbstractTasklet}.
     * @throws Exception para algum erro.
     */
    private AbstractTasklet getTasklet(Element tasklet) throws Exception {
        return (AbstractTasklet) Class.forName(tasklet.getAttributeValue(REF)).newInstance();
    }

}
