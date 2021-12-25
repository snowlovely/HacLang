package org.hac.parser;

import java.util.HashMap;

public  class Operators extends HashMap<String, Precedence> {
    public static boolean LEFT = true;
    public static boolean RIGHT = false;

    public void add(String name, int prec, boolean leftAssoc) {
        put(name, new Precedence(prec, leftAssoc));
    }
}