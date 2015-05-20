package com.galaev.classification.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * This class
 *
 * @author Anton Galaev
 */
public class Parameters {

    private Map<String, String> parameters = new HashMap<>();

    public void put(String parameterName, String parameterType) {
        parameters.put(parameterName, parameterType);
    }

    public Map<String, String> getParameters() {
        return Collections.unmodifiableMap(parameters);
    }

}
