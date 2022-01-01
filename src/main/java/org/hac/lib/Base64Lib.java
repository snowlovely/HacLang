package org.hac.lib;

import org.hac.exception.HacException;
import org.hac.function.NativeFunction;

import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class Base64Lib {
    private static final String LIB_NAME = "base64";
    public static List<NativeFunction> lib = new ArrayList<>();

    static {
        try {
            Method encode = Base64Lib.class.getMethod("encode", Object.class);
            lib.add(new NativeFunction(LIB_NAME + LibManager.SEP + "encode", encode));
            Method decode = Base64Lib.class.getMethod("decode", String.class);
            lib.add(new NativeFunction(LIB_NAME + LibManager.SEP + "decode", decode));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String encode(Object data) {
        if (data instanceof String) {
            return Base64.getEncoder().encodeToString(((String) data).getBytes(StandardCharsets.UTF_8));
        }
        if (data instanceof byte[]) {
            return Base64.getEncoder().encodeToString((byte[]) data);
        }
        throw new HacException("base64 encode error");
    }

    public static Object decode(String data) {
        return Base64.getDecoder().decode(data.getBytes(StandardCharsets.UTF_8));
    }
}
