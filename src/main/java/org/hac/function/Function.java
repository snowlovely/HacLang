package org.hac.function;

import org.hac.ast.BlockStmt;
import org.hac.ast.ParameterList;
import org.hac.env.BasicEnv;
import org.hac.env.Environment;

public class Function {
    protected ParameterList parameters;
    protected BlockStmt body;
    protected Object return0;
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

    public void setReturn0(Object return0) {
        this.return0 = return0;
    }

    public Object getReturn0() {
        return return0;
    }

    @Override
    public String toString() {
        return "<fun:" + hashCode() + ">";
    }
}
