package org.hac.core;

import org.hac.ast.BlockStmt;
import org.hac.ast.ParameterList;

public class Function {
    protected ParameterList parameters;
    protected BlockStmt body;
    protected Environment env;

    public Function(ParameterList parameters, BlockStmt body, Environment env) {
        this.parameters = parameters;
        this.body = body;
        this.env = env;
    }

    public ParameterList parameters() {
        return parameters;
    }

    public BlockStmt body() {
        return body;
    }

    public Environment makeEnv() {
        return new BasicEnv(env);
    }

    @Override
    public String toString() {
        return "<fun:" + hashCode() + ">";
    }
}
