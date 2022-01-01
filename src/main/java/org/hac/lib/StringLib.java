package org.hac.lib;

import org.hac.function.NativeFunction;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class StringLib {
    private static final String LIB_NAME = "string";
    public static List<NativeFunction> lib = new ArrayList<>();

    static {
        try {
            Method isEmpty = StringLib.class.getMethod("isEmpty", String.class);
            lib.add(new NativeFunction(LIB_NAME + LibManager.SEP + "isEmpty", isEmpty));
            Method contains = StringLib.class.getMethod("contains", String.class, String.class);
            lib.add(new NativeFunction(LIB_NAME + LibManager.SEP + "contains", contains));
            Method split = StringLib.class.getMethod("split", String.class, String.class);
            lib.add(new NativeFunction(LIB_NAME + LibManager.SEP + "split", split));
            Method substr = StringLib.class.getMethod("substr", String.class, int.class, int.class);
            lib.add(new NativeFunction(LIB_NAME + LibManager.SEP + "substr", substr));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int isEmpty(String data) {
        if (data == null) {
            return 1;
        }
        if (data.trim().equals("")) {
            return 1;
        }
        return 0;
    }

    public static int contains(String str, String data) {
        if (str == null || data == null) {
            return 0;
        }
        if (str.contains(data)) {
            return 1;
        }
        return 0;
    }

    public static Object[] split(String str, String data) {
        return str.split(data);
    }

    public static String substr(String str, int start, int end) {
        return str.substring(start, end);
    }
}
