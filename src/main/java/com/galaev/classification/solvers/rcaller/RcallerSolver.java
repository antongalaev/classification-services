package com.galaev.classification.solvers.rcaller;

import com.galaev.classification.model.Result;
import com.galaev.classification.solvers.Solver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import rcaller.RCaller;
import rcaller.RCode;

import java.io.IOException;

/**
 * This class
 *
 * @author Anton Galaev
 */
public abstract class RcallerSolver implements Solver {
    public static Logger logger = LogManager.getLogger();

    protected RCaller caller;
    protected RCode code;

    @Override
    public Result solve() {
        // creating an instance of RCaller and RCode class
        caller = new RCaller();
        code = new RCode();

        // set Rscript binary file
        caller.setRscriptExecutable("/usr/bin/Rscript");

        Result result;
        try {
            result = doSolve();
        } catch (IOException e) {
            logger.error("Error while running R scripts");
            throw new RuntimeException(e);
        }
        return result;
    }

    public abstract Result doSolve() throws IOException;
}
