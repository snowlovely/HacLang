package org.hac.natives;

import org.hac.env.Environment;
import org.hac.exception.HacException;
import org.hac.function.NativeFunction;

import java.lang.reflect.Method;

public class Natives {
    public Environment environment(Environment env) {
        appendNatives(env);
        return env;
    }

    protected void appendNatives(Environment env) {
        append(env, "print", Print.class, "print", Object.class);
        append(env, "length", Util.class, "length", String.class);
        append(env, "toInt", Convert.class, "toInt", Object.class);
        append(env, "currentTime", Time.class, "currentTime");
        append(env, "formatTime", Time.class, "formatTime");
        append(env, "readFile", File.class, "readFile", String.class);
        append(env, "writeFile", File.class, "writeFile", String.class, String.class);
    }

    protected void append(Environment env, String name, Class<?> clazz,
                          String methodName, Class<?>... params) {
        Method m;
        try {
            m = clazz.getMethod(methodName, params);
        } catch (Exception e) {
            throw new HacException("cannot find a native function: " + methodName);
        }
        env.put(name, new NativeFunction(methodName, m));
    }
}
