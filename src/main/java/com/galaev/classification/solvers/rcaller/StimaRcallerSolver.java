package com.galaev.classification.solvers.rcaller;

import com.galaev.classification.model.MVContext;
import com.galaev.classification.model.OutputClass;
import com.galaev.classification.model.Result;
import com.galaev.classification.util.StringUtils;
import rcaller.RCaller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * This class
 *
 * @author Anton Galaev
 */
public class StimaRcallerSolver extends RcallerSolver {

    @Override
    public Result doSolve(MVContext context) throws IOException {
        File inputFile = File.createTempFile("rcallerinput","");
        try
                (PrintWriter writer = new PrintWriter(new FileWriter(inputFile)))
        {
            writer.println(StringUtils.joinStrings(context.getAttributes().getList(), ","));

            for (List<String> values : context.getValues()) {
                writer.println(StringUtils.joinStrings(values, ","));
            }
        }

        code.R_require("stima");

        code.addRCode("all <- read.csv(\"" + inputFile.getAbsolutePath() + "\")");

        code.addRCode("stima1<-stima::stima(all,8,first=2)");

        code.addRCode("result <- list(nodes=stima1$trunk[,\"Region\"]," +
                "splitvars=stima1$trunk[,\"Predictor\"], " +
                "splitpoints=stima1$trunk[,\"Splitpoint\"])");

        caller.setRCode(code);
        caller.setFailurePolicy(RCaller.FailurePolicy.RETRY_FOREVER);
        caller.runAndReturnResult("result");

        String[] nodes = caller.getParser().getAsStringArray("nodes");
        String[] splitvars = caller.getParser().getAsStringArray("splitvars");
        String[] splitpoints = caller.getParser().getAsStringArray("splitpoints");

        Result result = new Result();

        for (int i = 0; i < nodes.length; i++) {
            String node = nodes[i];

            if (!node.isEmpty()) {
                OutputClass outputClass = new OutputClass();
                outputClass.setClassName(node);
                if (i % 2 == 1) {
                   outputClass.addLowerThanCondition(splitvars[i], splitpoints[i]);
                } else {
                    outputClass.addGreaterThanCondition(splitvars[i], splitpoints[i]);
                }
                result.addClass(outputClass);
            }
        }

        return result;

    }
}
