/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sicoob.cro.cop.util;

import java.io.InputStream;
import java.text.MessageFormat;
import java.util.Properties;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Classe responsavel por obter e fazer o tratamento de mensagens.
 *
 * @author Rogerio Alve Rodrigues
 */
public final class BatchPropertiesUtil {

    private static final Log LOG = LogFactory.getLog(BatchPropertiesUtil.class.getName());
    private Properties properties;
    private static BatchPropertiesUtil INSTANCE;
    private static final String FILE = "/batch_messages.properties";

    /**
     * Obtem uma nova instancia Singleton.
     *
     * @return um {@link BatchPropertiesUtil).
     */
    public static BatchPropertiesUtil getInstance() {
        if (Validation.isNull(INSTANCE)) {
            synchronized (BatchPropertiesUtil.class) {
                INSTANCE = new BatchPropertiesUtil();
            }
        }
        return INSTANCE;
    }

    /**
     * Construtor.
     */
    private BatchPropertiesUtil() {

    }

    /**
     * Carrega o arquivo properties na variavel {@code properties}.
     */
    private void loadProperties() {
        try {
            InputStream input = BatchPropertiesUtil.class.getResourceAsStream(FILE);
            this.properties.load(input);
        } catch (Exception ex) {
            LOG.fatal(ex);
        }
    }

    /**
     * Obtem uma mensagem pela chave.
     *
     * @param key chave.
     * @return Mensagem.
     */
    public String getMessage(String key) {
        initialize();
        return (String) properties.get(key);
    }

    /**
     * Obtem uma mensagem com a passagem de argumentos.
     *
     * @param key Chave.
     * @param replace Argumentos.
     * @return Mensagem formatada.
     */
    public String getMessage(String key, String... replace) {
        initialize();
        return applyArguments(key, replace);
    }

    /**
     * Inicializa o arquivo de properties/
     */
    private void initialize() {
        if (Validation.isNull(this.properties)) {
            this.properties = new Properties();
            loadProperties();
        }
    }

    /**
     * Passa os argumentos para a string da mensagem.
     *
     * @param key Chave.
     * @param replace Argumentos.
     * @return String formatada.
     */
    private String applyArguments(String key, String... replace) {
        MessageFormat format = new MessageFormat("");
        format.applyPattern((String) properties.get(key));
        return format.format(replace);
    }

}
