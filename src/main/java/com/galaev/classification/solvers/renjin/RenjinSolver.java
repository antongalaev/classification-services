package com.galaev.classification.solvers.renjin;

import com.galaev.classification.model.MVContext;
import com.galaev.classification.model.Result;
import com.galaev.classification.solvers.Solver;
import com.galaev.classification.util.RenjinFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.script.ScriptEngine;
import javax.script.ScriptException;

/**
 * This class
 *
 * @author Anton Galaev
 */
public abstract class RenjinSolver implements Solver {
    public static Logger logger = LogManager.getLogger();

    protected ScriptEngine engine;

    @Override
    public Result solve(MVContext context) {
        engine = RenjinFactory.getRenjin();
        Result result;

        try {
            result = doSolve();
        } catch (ScriptException e) {
            logger.error("Error while running R script");
            throw new RuntimeException(e);
        }
        return result;
    }

    public abstract Result doSolve() throws ScriptException;

}
