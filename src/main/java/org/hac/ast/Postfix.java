package org.hac.ast;

import org.hac.core.Environment;

import java.util.List;

public abstract class Postfix extends ASTList {
    public Postfix(List<ASTree> c) {
        super(c);
    }

    public abstract Object eval(Environment env, Object value);
}
