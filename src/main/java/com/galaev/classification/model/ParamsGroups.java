package com.galaev.classification.model;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is a representation of all parameters for an FCART solver.
 *
 * @author Anton Galaev
 */
public class ParamsGroups {

    private List<ParamsGroup> paramsGroups;

    public ParamsGroups() {
        paramsGroups = new ArrayList<>();
    }

    public List<ParamsGroup> getParamsGroups() {
        return paramsGroups;
    }

    public void setParamsGroups(List<ParamsGroup> paramsGroups) {
        this.paramsGroups = paramsGroups;
    }

    public void addParamsGroup(ParamsGroup paramsGroup) {
        paramsGroups.add(paramsGroup);
    }
}
