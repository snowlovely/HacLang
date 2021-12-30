package org.hac.ast;

import org.hac.env.Environment;

import java.util.List;

public class GoStmt extends ASTList {
    public GoStmt(List<ASTree> list) {
        super(list);
    }

    @Override
    public Object eval(Environment env) {
        new Thread(() -> child(0).eval(env)).start();
        return null;
    }
}
