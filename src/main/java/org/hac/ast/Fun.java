package org.hac.ast;

import org.hac.core.Environment;
import org.hac.core.Function;

import java.util.List;

public class Fun extends ASTList {
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
    public Object eval(Environment env) {
        return new Function(parameters(), body(), env);
    }
}
