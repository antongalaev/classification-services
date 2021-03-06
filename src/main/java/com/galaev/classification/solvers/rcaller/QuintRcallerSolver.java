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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class
 *
 * @author Anton Galaev
 */
public class QuintRcallerSolver extends RcallerSolver {

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

        code.R_require("quint");

        code.addRCode("all <- read.csv(\"" + inputFile.getAbsolutePath() + "\")");
//        code.addRCode("all <- read.csv(\"/Users/anton/Yandex.Disk/Diploma/R/quint.csv\")");

        code.addRCode("formula1 <- Time~Rand1 | Sex+ImmunCat+CNS+Mediastinum+Age+Leuc+Leber+Milz");
        code.addRCode("control1 <- quint.control(maxl=5,Bootstrap=FALSE,dmin=0.1)");
        code.addRCode("quint1 <- quint(formula1, data=all,control=control1)");

        code.addRCode("result <- list(nodes=quint1$li[,\"node\"], parents=quint1$si[,\"parentnode\"]," +
                "childs=as.character(quint1$si[,\"childnodes\"]), splitvars= quint1$si[,\"splittingvar\"], " +
                "splitpoints=quint1$si[,\"splitpoint\"], objects = as.numeric(quint1$nind))");

        caller.setRCode(code);
        caller.setFailurePolicy(RCaller.FailurePolicy.RETRY_FOREVER);
        caller.runAndReturnResult("result");

        Result result = new Result();
        List<String> nodes = new ArrayList<>();
        for (int i : caller.getParser().getAsIntArray("nodes")) {
            nodes.add(String.valueOf(i));
        }
        String[] parents = caller.getParser().getAsStringArray("parents");
        String[] childnodes = caller.getParser().getAsStringArray("childs");
        String[] splitvars = caller.getParser().getAsStringArray("splitvars");
        String[] splitpoints = caller.getParser().getAsStringArray("splitpoints");
        int[] objects = caller.getParser().getAsIntArray("objects");

        Map<String, OutputClass> allClasses = new HashMap<>();
        for (int i = 0; i < childnodes.length; ++i) {
            String[] classNames = childnodes[i].split(",");
            OutputClass parentClass = allClasses.get(parents[i]);

            OutputClass output1 = new OutputClass();
            output1.setClassName(classNames[0]);
            output1.addLowerThanCondition(splitvars[i], splitpoints[i]);
            if (parentClass != null) {
                output1.addRawCondition(parentClass.extractRawDescription());
            }
            allClasses.put(output1.getClassName(), output1);

            OutputClass output2 = new OutputClass();
            output2.setClassName(classNames[1]);
            output2.addGreaterThanCondition(splitvars[i], splitpoints[i]);
            if (parentClass != null) {
                output2.addRawCondition(parentClass.extractRawDescription());
            }
            allClasses.put(output2.getClassName(), output2);
        }

        for (OutputClass outputClass : allClasses.values()) {
            if (nodes.contains(outputClass.getClassName())) {
                result.addClass(outputClass);
            }
        }

        int objsCount = objects.length / result.getClassesCount();
        for (int i = 0; i < objects.length; ++i) {
            int nodeIndex = i / (objsCount + 1);
            int objIndex = i % (objsCount + 1);
            OutputClass outputClass = result.getClass(nodes.get(nodeIndex));
            if (objects[i] == 1) {
                outputClass.addObject(objIndex);
            }
        }

        return result;
    }

//    public static void main(String[] args) {
//        Result result = new QuintRcallerSolver().solve();
//        System.out.println(result);
//    }
}
