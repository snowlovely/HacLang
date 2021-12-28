package org.hac.ast;

import org.hac.core.Environment;

import java.util.List;

public class ArrayRef extends Postfix {
    public ArrayRef(List<ASTree> c) {
        super(c);
    }

    @Override
    public Object eval(Environment env, Object value) {
        return null;
    }

    public ASTree index() {
        return child(0);
    }

    @Override
    public String toString() {
        return "[" + index() + "]";
    }
}
