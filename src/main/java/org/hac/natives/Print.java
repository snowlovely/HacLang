package org.hac.natives;

import org.hac.exception.HacException;

import java.util.Arrays;

public class Print {
    public static int print(Object obj) {
        if (obj == null) {
            System.out.println("null");
            return 0;
        }
        System.out.println(obj.toString());
        return 0;
    }

    public static int printArray(Object[] obj) {
        if (obj == null) {
            System.out.println("null");
            return 0;
        }
        System.out.println(Arrays.toString(obj));
        return 0;
    }

    public static int printBytes(Object o){
        if (!(o instanceof byte[])){
            throw new HacException("print bytes error");
        }
        byte[] data = (byte[]) o;
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        for(Byte b:data){
            sb.append(b);
            sb.append(" ");
        }
        sb.append("]");
        print(sb.toString());
        return 0;
    }
}
