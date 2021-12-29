package org.hac.natives;

import java.util.Arrays;

public class Print {
    public static int print(Object obj) {
        System.out.println(obj.toString());
        return 0;
    }

    public static int printArray(Object[] obj){
        System.out.println(Arrays.toString(obj));
        return 0;
    }
}
