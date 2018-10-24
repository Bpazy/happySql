package com.github.bpazy.common.lang;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ArrayUtils {
    public static String toString(Object[] array) {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        int startLength = builder.length();

        for (Object o : array) {
            builder.append(o.toString())
                    .append(",\n");
        }
        if (builder.length() == startLength) {
            return builder.append("]").toString();
        }

        builder.deleteCharAt(builder.length() - 1);
        builder.deleteCharAt(builder.length() - 1);
        builder.append("]");
        return builder.toString();
    }
}
