package com.galaev.classification.model;

import java.util.ArrayList;
import java.util.List;

/**
 * This class
 *
 * @author Anton Galaev
 */
public class Result {

    private int classesCount;

    private List<OutputClass> classes;


    public Result() {
        classes = new ArrayList<>();
    }

    public int getClassesCount() {
        return classesCount;
    }

    public List<OutputClass> getClasses() {
        return classes;
    }

    public void addClass(OutputClass outputClass) {
        classes.add(outputClass);
        classesCount++;
    }

    public OutputClass getClass(String className) {
        for (OutputClass c : classes) {
            if (c.getClassName().equals(className)) {
                return c;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return classesCount + " " + classes;
    }
}
