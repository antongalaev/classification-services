package com.galaev.classification.model;

import java.util.ArrayList;
import java.util.List;

/**
 * This class
 *
 * @author Anton Galaev
 */
public class MVContextObjects {

    private int count;
    private List<String> list;

    public MVContextObjects() {
        list = new ArrayList<>();
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }
}
