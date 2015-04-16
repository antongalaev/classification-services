package com.galaev.classification.solvers.renjin;

import com.galaev.classification.model.Result;

import javax.script.ScriptException;

/**
 * This class
 *
 * @author Anton Galaev
 */
public class QuintRenjinSolver extends RenjinSolver {

    @Override
    public Result doSolve() throws ScriptException {
        engine.eval("df <- data.frame(x=1:10, y=(1:10)+rnorm(n=10))");
        engine.eval("print(df)");
        engine.eval("print(lm(y ~ x, df))");

        return null;
    }

}
