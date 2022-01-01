package org.hac.natives;

import org.hac.exception.HacException;

import java.util.HashMap;

@SuppressWarnings("unchecked")
public class Collection {
    public static HashMap<Object, Object> newMap() {
        return new HashMap<>();
    }

    public static int putMap(Object map, Object key, Object value) {
        if (!(map instanceof HashMap)) {
            throw new HacException("map error");
        }
        ((HashMap<Object, Object>) map).put(key, value);
        return 0;
    }

    public static Object getMap(Object map, Object key) {
        if (!(map instanceof HashMap)) {
            throw new HacException("map error");
        }
        return ((HashMap<Object, Object>) map).get(key);
    }

    public static Object clearMap(Object map) {
        if (!(map instanceof HashMap)) {
            throw new HacException("map error");
        }
        ((HashMap<Object, Object>) map).clear();
        return 0;
    }
}
