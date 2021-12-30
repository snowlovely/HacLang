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
            Method doGet = StringLib.class.getMethod("isEmpty", String.class);
            lib.add(new NativeFunction(LIB_NAME + LibManager.SEP + "isEmpty", doGet));
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
}
