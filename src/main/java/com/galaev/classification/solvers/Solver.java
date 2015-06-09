package com.galaev.classification.solvers;

import com.galaev.classification.model.MVContext;
import com.galaev.classification.model.Result;

/**
 * This class
 *
 * @author Anton Galaev
 */
public interface Solver {
    Result solve(MVContext context);
}
