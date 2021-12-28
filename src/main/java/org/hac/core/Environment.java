package org.hac.core;

public interface Environment {
    int TRUE = 1;
    int FALSE = 0;

    void put(String name, Object value);

    Object get(String name);
}
