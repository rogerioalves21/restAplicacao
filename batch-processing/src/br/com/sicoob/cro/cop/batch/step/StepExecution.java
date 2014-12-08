/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sicoob.cro.cop.batch.step;

import br.com.sicoob.cro.cop.batch.configuration.TaskletInjector;
import br.com.sicoob.cro.cop.batch.core.CoreExecution;
import br.com.sicoob.cro.cop.batch.core.Result;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.FutureTask;

/**
 * Classe responsavel por obter o tasklet do step e coloca-lo no contexto de
 * execucao do {@code executor}.
 *
 * @author Rogerio Alves Rodrigues
 */
public class StepExecution implements CoreExecution<Step, FutureTask<Result>> {

    @Override
    public FutureTask<Result> execute(Step item) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public FutureTask<Result> execute(Step item, ExecutorService executor) throws Exception {
        new TaskletInjector(item).inject();
        FutureTask<Result> task = new FutureTask<>(item.getTasklet());
        executor.execute(task);
        return task;
    }

}
