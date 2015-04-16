package com.galaev.classification.solvers.rcaller;

import com.galaev.classification.model.Result;

import java.io.File;
import java.io.IOException;

/**
 * This class
 *
 * @author Anton Galaev
 */
public class StimaRcallerSolver extends RcallerSolver {

    @Override
    public Result doSolve() throws IOException {
        code.R_require("stima");

        code.addRCode("all <- read.csv(\"/Users/anton/Yandex.Disk/Diploma/Project/all2006.csv\")");
        code.addRCode("all2 <- all[, c(3,2,4:12)]");

        code.addRCode("stima1<-stima::stima(all2,5,first=2)");

        File plot = code.startPlot();
        code.addRCode("stima::plot.rt(stima1)");
        code.endPlot();

        caller.setRCode(code);
        caller.runOnly();

        code.showPlot(plot);

        return null;

    }
}
