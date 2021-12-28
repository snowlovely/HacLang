package org.hac.ast;

import org.hac.core.Environment;
import org.hac.exception.HacException;

import java.util.List;

public class ArrayRef extends Postfix {
    public ArrayRef(List<ASTree> c) {
        super(c);
    }

    public ASTree index() {
        return child(0);
    }

    @Override
    public String toString() {
        return "[" + index() + "]";
    }

    @Override
    public Object eval(Environment env, Object value) {
        if (value instanceof Object[]) {
            Object index = index().eval(env);
            if (index instanceof Integer) {
                return ((Object[]) value)[(Integer) index];
            }
        }
        throw new HacException("bad array access", this);
    }
}
