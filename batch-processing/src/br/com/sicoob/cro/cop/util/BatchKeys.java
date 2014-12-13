/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sicoob.cro.cop.util;

/**
 *
 * @author rogerioalves21
 */
public enum BatchKeys {
    
    RUNNING_JOB("runningJob"),
    RESULT("result"),
    STATUS("status"),
    ADD_ERROR_MESSAGE("addErrorMessage"),
    BATCH_CONFIGURATIONS_ANNOTATION("batch.configurations.verificacao"),
    BATCH_CONFIGURATIONS_JOB("batch.configurations.jobs"),
    BATCH_CONFIGURATIONS_NOTFOUND("batch.configurations.naoencontrado"),
    BATCH_CONFIGURATIONS_JOB_NOTFOUND("batch.configurations.nenhum.job"),
    BATCH_LAUNCHER_INITIALIZING("batch.launcher.executor.iniciando"),
    BATCH_LAUNCHER_ENDING("batch.launcher.executor.finalizando"),
    BATCH_LAUNCHER_ERROR_ENDING("batch.launcher.executor.finalizando.erro"),
    BATCH_LAUNCHER_JOB_ERROR_ENDING("batch.launcher.executor.finalizando.erro.job")
    ;
        private String key;
        
        private BatchKeys(String key) {
            this.key = key;
        }
        
        public String getKey() {
            return this.key;
        }
    
}
