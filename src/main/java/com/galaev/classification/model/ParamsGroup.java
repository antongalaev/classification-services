package com.galaev.classification.model;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a params group for an FCART solver.
 *
 * @author Anton Galaev
 */
public class ParamsGroup {

    private String group;
    private String caption;
    private List<Parameter> params;

    public ParamsGroup() {
        params = new ArrayList<>();
    }

    public void addParameter(Parameter parameter) {
        params.add(parameter);
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public List<Parameter> getParams() {
        return params;
    }

    public void setParams(List<Parameter> params) {
        this.params = params;
    }
}
