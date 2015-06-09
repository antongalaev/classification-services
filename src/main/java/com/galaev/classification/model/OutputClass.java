package com.galaev.classification.model;

import java.util.ArrayList;
import java.util.List;

/**
 * This class
 *
 * @author Anton Galaev
 */
public class OutputClass {

    private String className;

    private List<Integer> objects;

    private StringBuilder description;
    private boolean started;

    public OutputClass() {
        className = "";
        objects = new ArrayList<>();
        description = new StringBuilder();
        description.append("{");
    }


    public void addEqualsCondition(String parameter, Object value) {
        checkStarted();
        description.append(" ")
                .append(parameter)
                .append(": ")
                .append(encloseInQuotes(value));
    }

    public void addLowerThanCondition(String parameter, Object value) {
        checkStarted();
        description.append(" ")
                .append(parameter)
                .append(": { $lt: ")
                .append(encloseInQuotes(value)).append("}");
    }

    public void addGreaterThanCondition(String parameter, Object value) {
        checkStarted();
        description.append(" ")
                .append(parameter)
                .append(": { $gt: ")
                .append(encloseInQuotes(value)).append("}");
    }

    public StringBuilder getDescription() {
        return description.append("}");
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<Integer> getObjects() {
        return objects;
    }

    public void setObjects(List<Integer> objects) {
        this.objects = objects;
    }

    public void addObject(int index) {
        objects.add(index);
    }

    private String encloseInQuotes(Object value) {
        return "'" + value + "'";
    }

    private void checkStarted() {
        if (started) {
            description.append(",");
        } else {
            started = true;
        }
    }

    @Override
    public String toString() {
        return className + " " + description + " " + objects;
    }
}
