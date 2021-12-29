package org.hac.lib;

import org.hac.function.NativeFunction;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class HttpLib {
    public static List<NativeFunction> lib = new ArrayList<>();

    static {
        try {
            Method doGet = HttpLib.class.getMethod("doGet");
            lib.add(new NativeFunction("doGet", doGet));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String[] doGet() {
        System.out.println("http lib");
        return new String[]{"1","2","3"};
    }
}
