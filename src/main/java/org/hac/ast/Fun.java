package org.hac.ast;

import org.hac.core.Environment;
import org.hac.core.Function;
import org.hac.core.OptFunction;
import org.hac.core.Symbols;

import java.util.List;

public class Fun extends ASTList {
    private int size = -1;

    public Fun(List<ASTree> c) {
        super(c);
    }

    public ParameterList parameters() {
        return (ParameterList) child(0);
    }

    public BlockStmt body() {
        return (BlockStmt) child(1);
    }

    @Override
    public String toString() {
        return "(fun " + parameters() + " " + body() + ")";
    }

    @Override
    public void lookup(Symbols sym) {
        size = lookup(sym, parameters(), body());
    }

    @Override
    public Object eval(Environment env) {
        return new OptFunction(parameters(), body(), env, size);
    }

    public static int lookup(Symbols sym, ParameterList params, BlockStmt body) {
        Symbols newSym = new Symbols(sym);
        params.lookup(newSym);
        body.lookup(newSym);
        return newSym.size();
    }
}
