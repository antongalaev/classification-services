package com.galaev.classification.model;

/**
 * Types of parameters available in FCART solvers.
 *
 * @author Anton Galaev
 */
public enum ParameterType {
    STRING, INT, DOUBLE, OPT;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
