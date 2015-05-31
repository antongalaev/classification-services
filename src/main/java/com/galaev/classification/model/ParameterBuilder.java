package com.galaev.classification.model;

/**
 * This class is a builder for
 * Parameter class.
 *
 * @author Anton Galaev
 */
public class ParameterBuilder {

    private String paramName;
    private String paramCaption;
    private ParameterType paramType;
    private String paramHint;
    private String paramDefaultValue;
    private String paramInfo;

    public ParameterBuilder setParamName(String paramName) {
        this.paramName = paramName;
        return this;
    }

    public ParameterBuilder setParamCaption(String paramCaption) {
        this.paramCaption = paramCaption;
        return this;
    }

    public ParameterBuilder setParamType(ParameterType paramType) {
        this.paramType = paramType;
        return this;
    }

    public ParameterBuilder setParamHint(String paramHint) {
        this.paramHint = paramHint;
        return this;
    }

    public ParameterBuilder setParamDefaultValue(String paramDefaultValue) {
        this.paramDefaultValue = paramDefaultValue;
        return this;
    }

    public ParameterBuilder setParamInfo(String paramInfo) {
        this.paramInfo = paramInfo;
        return this;
    }

    public Parameter build() {
        Parameter parameter = new Parameter();
        parameter.setParamName(paramName);
        parameter.setParamCaption(paramCaption);
        parameter.setParamType(paramType);
        parameter.setParamHint(paramHint);
        parameter.setParamDefaultValue(paramDefaultValue);
        parameter.setParamInfo(paramInfo);
        return parameter;
    }
}
