/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sicoob.cro.cop.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.ResourceBundle;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author rogerioalves21
 */
public class BatchPropertiesUtil {
    
    private static final Log LOG = LogFactory.getLog(BatchPropertiesUtil.class.getName());
    private static Properties properties;
    
    public static BatchPropertiesUtil getInstance() {
        return new BatchPropertiesUtil();
    }
    
    private BatchPropertiesUtil() {
        try {
            String userDir = System.getProperty("user.dir").concat(File.separator).concat("src").concat(File.separator);
            new FileInputStream(userDir.concat("cop_batch_messages.properties"));
            //ResourceBundle.getBundle(userDir.concat("cop_batch_messages.properties"));
            properties.load(getClass().getResourceAsStream("/cop_batch_messages.properties"));
        } catch (IOException excecao) {
            LOG.fatal("Erro ao ler o arquivo de propriedades", excecao);
        }
    }
    
    public String getMessage(String key) {
        return (String) properties.get(key);
    }
    
    public String getMessage(String key, String... replace) {
        String message = (String) properties.get(key);
        if (replace != null) {
            int contador = 0;
            for (String obj : replace) {
                message = message.replaceAll("{" + contador + "}", obj);
            }
        }
        return message;
    }
    
}
