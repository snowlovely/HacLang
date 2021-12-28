package org.hac.ast;

import org.hac.core.Environment;
import org.hac.core.Function;
import org.hac.exception.HacException;

import java.util.List;

public class Arguments extends Postfix {
    public Arguments(List<ASTree> c) {
        super(c);
    }

    @Override
    public Object eval(Environment callerEnv, Object value) {
        if (!(value instanceof Function)) {
            throw new HacException("bad function", this);
        }
        Function func = (Function) value;
        ParameterList params = func.parameters();
        if (size() != params.size()) {
            throw new HacException("bad number of arguments", this);
        }
        Environment newEnv = func.makeEnv();
        int num = 0;
        for (ASTree a : this) {
            params.eval(newEnv, num++, a.eval(callerEnv));
        }
        return func.body().eval(newEnv);
    }

    public int size() {
        return numChildren();
    }
}
