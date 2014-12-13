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
    ADD_ERROR_MESSAGE("addErrorMessage")
    ;
        private String key;
        
        private BatchKeys(String key) {
            this.key = key;
        }
        
        public String getKey() {
            return this.key;
        }
    
}
