package org.hac.ast;

import org.hac.core.Environment;
import org.hac.core.Function;
import org.hac.core.OptFunction;
import org.hac.core.Symbols;

import java.util.List;

public class DefStmt extends ASTList {
    private int size;
    private int index;

    public DefStmt(List<ASTree> c) {
        super(c);
    }

    public String name() {
        return ((ASTLeaf) child(0)).token().getText();
    }

    public ParameterList parameters() {
        return (ParameterList) child(1);
    }

    public BlockStmt body() {
        return (BlockStmt) child(2);
    }

    @Override
    public String toString() {
        return "(def " + name() + " " + parameters() + " " + body() + ")";
    }

    @Override
    public void lookup(Symbols sym) {
        index = sym.putNew(name());
        size = Fun.lookup(sym, parameters(), body());
    }

    @Override
    public Object eval(Environment env) {
        env.put(0, index, new OptFunction(parameters(), body(), env, size));
        return name();
    }
}
