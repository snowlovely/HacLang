package org.hac.ast;

import org.hac.env.Environment;
import org.hac.exception.HacException;

import java.util.List;

public class NegativeExpr extends ASTList {
    public NegativeExpr(List<ASTree> c) {
        super(c);
    }

    public ASTree operand() {
        return child(0);
    }

    @Override
    public String toString() {
        return "-" + operand();
    }

    @Override
    public Object eval(Environment env) {
        Object v = operand().eval(env);
        if (v instanceof Integer) {
            return -(Integer) v;
        } else {
            throw new HacException("bad type for -", this);
        }
    }
}
