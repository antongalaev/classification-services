package com.galaev.classification.solvers.rcaller;

import com.galaev.classification.model.OutputClass;
import com.galaev.classification.model.Result;
import rcaller.RCaller;

import java.io.IOException;
import java.util.Arrays;

/**
 * This class
 *
 * @author Anton Galaev
 */
public class QuintRcallerSolver extends RcallerSolver {

    @Override
    public Result doSolve() throws IOException {

        code.R_require("quint");

        code.addRCode("all <- read.csv(\"/Users/anton/Yandex.Disk/Diploma/R/quint.csv\")");

        code.addRCode("formula1 <- Time~Rand1 | Sex+ImmunCat+CNS+Mediastinum+Age+Leuc+Leber+Milz");
        code.addRCode("control1 <- quint.control(maxl=5,Bootstrap=FALSE,dmin=0.1)");
        code.addRCode("quint1 <- quint(formula1, data=all,control=control1)");

        caller.setRCode(code);
        caller.setFailurePolicy(RCaller.FailurePolicy.RETRY_FOREVER);
        caller.runAndReturnResult("quint1$si");

        Result result = new Result();
        OutputClass output = new OutputClass();
        output.setClassName(Arrays.toString(caller.getParser().getAsDoubleArray("parentnode")));
        result.addClass(output);

        return result;
    }

    public static void main(String[] args) {
        Result result = new QuintRcallerSolver().solve();
        System.out.println(result.getClasses().get(0).getClassName());
    }
}
