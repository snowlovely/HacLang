package org.hac.ast;

import org.hac.core.Environment;

import java.util.List;

public class Dot extends Postfix {
    public Dot(List<ASTree> c) {
        super(c);
    }

    @Override
    public Object eval(Environment env, Object value) {
        return null;
    }

    public String name() {
        return ((ASTLeaf) child(0)).token().getText();
    }

    @Override
    public String toString() {
        return "." + name();
    }
}