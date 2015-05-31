package com.galaev.classification.model;

/**
 * This class represents a parameter for an FCART solver.
 *
 * @author Anton Galaev
 */
public class Parameter {

    private String paramName;
    private String paramCaption;
    private ParameterType paramType;
    private String paramHint;
    private String paramDefaultValue;
    private String paramInfo;

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public String getParamCaption() {
        return paramCaption;
    }

    public void setParamCaption(String paramCaption) {
        this.paramCaption = paramCaption;
    }

    public ParameterType getParamType() {
        return paramType;
    }

    public void setParamType(ParameterType paramType) {
        this.paramType = paramType;
    }

    public String getParamHint() {
        return paramHint;
    }

    public void setParamHint(String paramHint) {
        this.paramHint = paramHint;
    }

    public String getParamDefaultValue() {
        return paramDefaultValue;
    }

    public void setParamDefaultValue(String paramDefaultValue) {
        this.paramDefaultValue = paramDefaultValue;
    }

    public String getParamInfo() {
        return paramInfo;
    }

    public void setParamInfo(String paramInfo) {
        this.paramInfo = paramInfo;
    }
}
