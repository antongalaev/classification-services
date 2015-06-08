package com.galaev.classification.model;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents multi-valued context.
 *
 * @author Anton Galaev
 */
public class MVContext {

    private MVContextObjects objects;
    private MVContextAttributes attributes;
    private List<List<String>> values;

    public MVContext() {
        objects = new MVContextObjects();
        attributes = new MVContextAttributes();
        values = new ArrayList<>();
    }

    public MVContextObjects getObjects() {
        return objects;
    }

    public void setObjects(MVContextObjects objects) {
        this.objects = objects;
    }

    public MVContextAttributes getAttributes() {
        return attributes;
    }

    public void setAttributes(MVContextAttributes attributes) {
        this.attributes = attributes;
    }

    public List<List<String>> getValues() {
        return values;
    }

    public void setValues(List<List<String>> values) {
        this.values = values;
    }

    public void addValuesRow(List<String> row) {
        values.add(row);
    }
}
