/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sicoob.cro.cop.util;

/**
 * Constantes para o sistema e arquivo de propriedaes.
 *
 * @author Rogerio Alves Rodrigues
 */
public enum BatchKeys {

    RUNNING_JOB("runningJob"),
    RESULT("result"),
    STATUS("status"),
    PARAMETERS("parameters"),
    JOB_BUILDER("@JobBuilderFactory"),
    STEP_BUILDER("@StepBuilderFactory"),
    CONTEXT("@Context"),
    ID("id"),
    STEPS("steps"),
    MODE("mode"),
    TASKLET("tasklet"),
    TYPE("type"),
    ADD_ERROR_MESSAGE("addErrorMessage"),
    BATCH_CONFIGURATIONS_ANNOTATION("batch.configurations.verificacao"),
    BATCH_CONFIGURATIONS_JOB("batch.configurations.jobs"),
    BATCH_CONFIGURATIONS_NOTFOUND("batch.configurations.naoencontrado"),
    BATCH_CONFIGURATIONS_JOB_NOTFOUND("batch.configurations.nenhum.job"),
    BATCH_LAUNCHER_INITIALIZING("batch.launcher.executor.iniciando"),
    BATCH_LAUNCHER_ENDING("batch.launcher.executor.finalizando"),
    BATCH_LAUNCHER_ERROR_ENDING("batch.launcher.executor.finalizando.erro"),
    BATCH_LAUNCHER_JOB_ERROR_ENDING("batch.launcher.executor.finalizando.erro.job"),
    BATCH_INJECTOR_INFO("batch.injector.info"),
    BATCH_JOB_EXECUTOR_FINALIZED("batch.job.executor.finalizado"),
    JOB_ID("job.id"),
    JOB_LENGHT("job.quantidade"),
    JOB_MODE("job.modo"),
    MANDATORY_FIELD("campo.obrigatorio")
    ;
    private String key;

    private BatchKeys(String key) {
        this.key = key;
    }

    public String getKey() {
        return this.key;
    }

}
