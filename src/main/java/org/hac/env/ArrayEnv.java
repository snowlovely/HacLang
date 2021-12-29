package org.hac.env;

import org.hac.exception.HacException;

public class ArrayEnv implements Environment {
    protected Object[] values;
    protected Environment outer;
    public ArrayEnv(int size, Environment out) {
        values = new Object[size];
        outer = out;
    }
    public Symbols symbols() { throw new HacException("no symbols"); }
    public Object get(int nest, int index) {
        if (nest == 0){
            return values[index];}
        else if (outer == null) {
            return null;
        }
        else {
            return  outer.get(nest - 1, index);
        }
    }
    public void put(int nest, int index, Object value) {
        if (nest == 0) {
            values[index] = value;
        }else if (outer == null) {
            throw new HacException("no outer environment");
        }else{
            outer.put(nest - 1, index, value);
    }}
    public Object get(String name) { error(name); return null; }
    public void put(String name, Object value) { error(name); }
    public void putNew(String name, Object value) { error(name); }
    public Environment where(String name) { error(name); return null; }
    public void setOuter(Environment e) { outer = e; }
    private void error(String name) {
        throw new HacException("cannot access by name: " + name);
    }
}