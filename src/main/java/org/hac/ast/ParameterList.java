package org.hac.ast;

import org.hac.core.Environment;

import java.util.List;

public class ParameterList extends ASTList {
    public ParameterList(List<ASTree> c) {
        super(c);
    }

    public String name(int i) {
        return ((ASTLeaf) child(i)).token().getText();
    }

    public int size() {
        return numChildren();
    }

    public void eval(Environment env, int index, Object value) {
        env.putNew(name(index), value);
    }
}
