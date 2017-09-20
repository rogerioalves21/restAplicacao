package br.com.sicoob.cro.cop.util;

/**
 * Constantes para o sistema e arquivo de propriedaes.
 *
 * @author Rogerio Alves Rodrigues
 */
public enum BatchKeys {

    CABANOVO("NOVO"),
    RUNNING_JOB("job"),
    RESULT("result"),
    STATUS("status"),
    PARAMETERS("parameters"),
    JOB_BUILDER("@JobBuilderFactory"),
    STEP_BUILDER("@StepBuilderFactory"),
    CONTEXT("@Context"),
    ID("id"),
    STEPS("steps"),
    STEP("step"),
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
    MANDATORY_FIELD("campo.obrigatorio"),
    COMMIT_INTERVAL_DEFAULT("chunk.commit.interval.default"),
    BATCH_LOADER_SUCCESS("job.reader.criacao.sucesso"),
    BATCH_LOADER_FAIL("job.reader.criacao.erro")
    ;
    private String key;

    private BatchKeys(String key) {
        this.key = key;
    }

    public String getKey() {
        return this.key;
    }

}
