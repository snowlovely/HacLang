package org.hac.lib;

import org.hac.env.Environment;
import org.hac.function.NativeFunction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LibManager {
    private static final Map<String, List<NativeFunction>> LIB_LIST = new HashMap<>();

    static {
        LIB_LIST.put("core/http", HttpLib.lib);
    }

    public static void addLib(String libName, Environment env) {
        if (LIB_LIST.containsKey(libName)) {
            List<NativeFunction> list = LIB_LIST.get(libName);
            for (NativeFunction function : list) {
                env.put(function.getName(), function);
            }
        }
    }
}
