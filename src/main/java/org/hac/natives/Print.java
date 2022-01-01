package org.hac.natives;

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
}
