/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sicoob.cro.cop.batch.configuration;

import br.com.sicoob.cro.cop.batch.core.DataExecution;
import br.com.sicoob.cro.cop.batch.core.BatchExecution;
import br.com.sicoob.cro.cop.batch.core.launcher.Launcher;
import br.com.sicoob.cro.cop.batch.core.launcher.SimpleJobLauncher;
import com.google.inject.Binder;
import com.google.inject.Module;

/**
 *
 * @author Rogerio Alves Rodrigues
 */
public class BatchProcessModule implements Module {

    public void configure(Binder binder) {
        binder.bind(Launcher.class).to(SimpleJobLauncher.class);
        binder.bind(BatchExecution.class).to(DataExecution.class);
    }

}
