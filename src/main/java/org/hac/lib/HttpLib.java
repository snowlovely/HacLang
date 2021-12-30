package org.hac.lib;

import kotlin.Pair;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.hac.function.NativeFunction;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class HttpLib {
    private static final String LIB_NAME = "http";
    public static List<NativeFunction> lib = new ArrayList<>();

    static {
        try {
            Method doGet = HttpLib.class.getMethod("doGet", String.class, Object[].class);
            lib.add(new NativeFunction(LIB_NAME + LibManager.SEP + "doGet", doGet));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String[][] doGet(String url, Object[] headers) {
        String[][] data = new String[3][];
        try {
            OkHttpClient client = new OkHttpClient();
            Request.Builder builder = new Request.Builder().url(url).get();
            for (Object obj : headers) {
                String[] split = obj.toString().split(": ");
                String key = split[0];
                StringBuilder value;
                if (split.length == 2) {
                    value = new StringBuilder(obj.toString().split(": ")[1]);
                } else if (split.length < 2) {
                    break;
                } else {
                    value = new StringBuilder();
                    for (int i = 0; i < split.length; i++) {
                        if (i != split.length - 1) {
                            value.append(split[i]).append(": ");
                        } else {
                            value.append(split[i]);
                        }
                    }
                }
                builder.addHeader(key, value.toString());
            }
            Request req = builder.build();
            Call call = client.newCall(req);
            Response response = call.execute();
            data[0] = new String[1];
            data[0][0] = Integer.toString(response.code());
            Iterator<Pair<String, String>> iterable = response.headers().iterator();
            data[1] = new String[response.headers().size()];
            int i = 0;
            while (iterable.hasNext()) {
                Pair<String, String> temp = iterable.next();
                data[1][i] = temp.getFirst() + ": " + temp.getSecond();
                i++;
            }
            data[2] = new String[1];
            data[2][0] = Objects.requireNonNull(response.body()).string();
            response.close();
            return data;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }
}
