package org.hac.ast;

import org.hac.env.Environment;
import org.hac.env.Symbols;

import java.util.Iterator;

public abstract class ASTree implements Iterable<ASTree> {
    public abstract ASTree child(int i);

    public abstract int numChildren();

    public abstract Iterator<ASTree> children();

    public abstract String location();

    @Override
    public Iterator<ASTree> iterator() {
        return children();
    }

    public abstract Object eval(Environment env);

    public void lookup(Symbols sym) {}
}