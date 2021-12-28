package org.hac.core;

import org.hac.ast.BlockStmt;
import org.hac.ast.ParameterList;

public class OptFunction extends Function {
    protected int size;

    public OptFunction(ParameterList parameters, BlockStmt body,
                       Environment env, int memorySize) {
        super(parameters, body, env);
        size = memorySize;
    }

    @Override
    public Environment makeEnv() {
        return new ArrayEnv(size, env);
    }
}
