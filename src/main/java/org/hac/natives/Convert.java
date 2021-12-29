package org.hac.natives;

public class Convert {
    public static int toInt(Object value) {
        if (value instanceof String) {
            return Integer.parseInt((String) value);
        } else if (value instanceof Integer) {
            return (Integer) value;
        } else {
            throw new NumberFormatException(value.toString());
        }
    }
}
