package org.hac.lib;

import okhttp3.*;
import org.hac.exception.HacException;
import org.hac.function.NativeFunction;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.*;

@SuppressWarnings("unchecked")
public class HttpLib {
    private static final String LIB_NAME = "http";
    public static List<NativeFunction> lib = new ArrayList<>();

    static {
        try {
            Method doGet = HttpLib.class.getMethod("doGet",
                    String.class, Object.class);
            lib.add(new NativeFunction(LIB_NAME + LibManager.SEP + "doGet", doGet));
            Method doPost = HttpLib.class.getMethod("doPost",
                    String.class, Object.class, String.class, String.class);
            lib.add(new NativeFunction(LIB_NAME + LibManager.SEP + "doPost", doPost));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Object doGet(String url, Object headers) {
        checkMap(headers);
        try {
            HashMap<Object, Object> returnValue = new HashMap<>();
            OkHttpClient client = new OkHttpClient();
            Request.Builder builder = new Request.Builder().url(url).get();
            resolveHeaders(headers, builder);
            Request req = builder.build();
            Call call = client.newCall(req);
            Response response = call.execute();
            getResponse(returnValue, response);
            return returnValue;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Object doPost(String url, Object headers, String body, String contentType) {
        checkMap(headers);
        try {
            HashMap<Object, Object> returnValue = new HashMap<>();
            OkHttpClient client = new OkHttpClient();
            Request.Builder builder = new Request.Builder().url(url);
            if (contentType.equals("form")) {
                contentType = "application/x-www-form-urlencoded";
            } else if (contentType.equals("json")) {
                contentType = "application/json";
            }
            MediaType type = MediaType.parse(contentType);
            RequestBody requestBody = RequestBody.create(type, body);
            builder.post(requestBody);
            resolveHeaders(headers, builder);
            Request req = builder.build();
            Call call = client.newCall(req);
            Response response = call.execute();
            getResponse(returnValue, response);
            return returnValue;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void checkMap(Object headers) {
        if (!(headers instanceof HashMap)) {
            throw new HacException("headers error");
        }
    }

    private static void resolveHeaders(Object headers, Request.Builder builder) {
        HashMap<Object, Object> headersMap = (HashMap<Object, Object>) headers;
        for (Map.Entry<Object, Object> obj : headersMap.entrySet()) {
            String key = (String) obj.getKey();
            String value = (String) obj.getValue();
            builder.addHeader(key, value);
        }
    }

    private static void getResponse(HashMap<Object, Object> returnValue,
                                    Response response) throws IOException {
        returnValue.put("code", response.code());
        Set<String> names = response.headers().names();
        HashMap<Object, Object> retHeaders = new HashMap<>();
        for (String name : names) {
            retHeaders.put(name, response.headers().get(name));
        }
        returnValue.put("body", Objects.requireNonNull(response.body()).string());
        returnValue.put("headers", retHeaders);
        response.close();
    }
}
