package org.hac.lib;

import okhttp3.*;
import org.hac.exception.HacException;
import org.hac.function.NativeFunction;
import org.hac.util.StringUtil;

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
            Method doRequest = HttpLib.class.getMethod("doRequest", String.class);
            lib.add(new NativeFunction(LIB_NAME + LibManager.SEP + "doRequest", doRequest));
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

    public static Object doRequest(String request) {
        try {
            String lineSep = System.lineSeparator();
            String[] lines = request.split(lineSep + lineSep);
            StringBuilder requestBodyBuilder = new StringBuilder();
            if (lines.length == 1) {
                requestBodyBuilder = null;
            } else if (lines.length > 1) {
                for (int i = 1; i < lines.length; i++) {
                    requestBodyBuilder.append(lines[i]);
                }
            } else {
                throw new HacException("request data error");
            }
            String requestBody = null;
            if (requestBodyBuilder != null) {
                requestBody = requestBodyBuilder.toString().trim();
            }

            String firstContent = lines[0];
            String[] splits = firstContent.split(lineSep);
            if (splits.length < 1) {
                throw new HacException("request data error");
            }
            String info = splits[0];
            String[] temp = info.split(" ");
            String requestMethod = temp[0];
            String path = temp[1];

            HashMap<Object, Object> headers = new HashMap<>();
            for (int i = 1; i < splits.length; i++) {
                String[] innerTemp = splits[i].split(": ");
                String key = innerTemp[0];
                StringBuilder innerBuilder = new StringBuilder();
                for (int j = 1; j < innerTemp.length; j++) {
                    innerBuilder.append(innerTemp[j]);
                    if (j != innerTemp.length - 1) {
                        innerBuilder.append(": ");
                    }
                }
                String value = innerBuilder.toString();
                headers.put(key, value);
            }
            String contentType = (String) headers.remove("Content-Type");
            String host = (String) headers.remove("Host");
            String url = "http" + "://" + host + path;
            HashMap<Object, Object> returnValue = new HashMap<>();
            OkHttpClient client = new OkHttpClient();
            Request.Builder builder = new Request.Builder().url(url);
            if (!StringUtil.isEmpty(contentType) &&
                    !StringUtil.isEmpty(requestBody) &&
                    !requestMethod.equalsIgnoreCase("GET")) {
                MediaType type = MediaType.parse(contentType);
                RequestBody body = RequestBody.create(type, requestBody);
                builder.method(requestMethod, body);
            } else {
                builder.method(requestMethod, null);
            }
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
