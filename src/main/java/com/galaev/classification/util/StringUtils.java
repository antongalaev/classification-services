package com.galaev.classification.util;

import java.util.List;

/**
 * This class
 *
 * @author Anton Galaev
 */
public class StringUtils {

    public static String joinStrings(List<String> list, String separator) {
        StringBuilder builder = new StringBuilder();
        builder.append(list.get(0));
        for (int i = 1; i < list.size(); i++) {
            String s = list.get(i);
            builder.append(separator).append(s);
        }
        return builder.toString();
    }

}
