package com.galaev.classification.solvers.rcaller;

import com.galaev.classification.model.Result;

import java.io.File;
import java.io.IOException;

/**
 * This class
 *
 * @author Anton Galaev
 */
public class QuintRcallerSolver extends RcallerSolver {

    @Override
    public Result doSolve() throws IOException {

        code.R_require("quint");

        code.addRCode("all <- read.csv(\"/Users/anton/Yandex.Disk/Diploma/Project/all2006.csv\")");

        code.addRCode("formula1 <- Tod~Rand1 | Time+Sex+ImmunCat+CNS+Mediastinum+Age+Leuc+Leber+Milz+ImmunNum");
        code.addRCode("control1 <- quint::quint.control(maxl=5,B=2,dmin=0.1)");
        code.addRCode("quint1 <- quint::quint(formula1, data=all,control=control1)");

        File plot = code.startPlot();
        code.addRCode("plot(quint1)");
        code.endPlot();

        caller.setRCode(code);
        caller.runOnly();

        code.showPlot(plot);

        return null;
    }
}
