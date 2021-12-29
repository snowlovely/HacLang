package org.hac.ast;

import org.hac.env.Environment;
import org.hac.lib.LibManager;

import java.util.List;

public class IncludeStmt extends ASTList{
    public IncludeStmt(List<ASTree> list) {
        super(list);
    }

    @Override
    public Object eval(Environment env) {
        String includeName = (String) child(0).eval(env);
        LibManager.addLib(includeName);
        return null;
    }
}
