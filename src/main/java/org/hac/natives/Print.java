package org.hac.natives;

import org.hac.env.Environment;
import org.hac.exception.HacException;

import java.util.Arrays;

public class Print {
    public static int print(Object obj) {
        if (obj == null) {
            System.out.println("null");
            return Environment.TRUE;
        }
        System.out.println(obj.toString());
        return Environment.FALSE;
    }

    public static int printArray(Object[] obj) {
        if (obj == null) {
            System.out.println("null");
            return Environment.TRUE;
        }
        System.out.println(Arrays.toString(obj));
        return Environment.FALSE;
    }

    public static int printBytes(Object o) {
        if (!(o instanceof byte[])) {
            throw new HacException("print bytes error");
        }
        byte[] data = (byte[]) o;
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        for (Byte b : data) {
            sb.append(b);
            sb.append(" ");
        }
        sb.append("]");
        print(sb.toString());
        return Environment.TRUE;
    }
}
