package org.hac.ast;

import org.hac.core.Threads;
import org.hac.env.Environment;

import java.util.List;
import java.util.UUID;

public class GoStmt extends ASTList {
    public GoStmt(List<ASTree> list) {
        super(list);
    }

    @Override
    public Object eval(Environment env) {
        String key = UUID.randomUUID().toString();
        Threads.add(key, new Thread(() -> child(0).eval(env)));
        Threads.start(key);
        return null;
    }
}
