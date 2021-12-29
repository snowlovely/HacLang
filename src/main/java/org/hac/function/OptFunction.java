package org.hac.function;

import org.hac.ast.BlockStmt;
import org.hac.ast.ParameterList;
import org.hac.env.ArrayEnv;
import org.hac.env.Environment;

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
