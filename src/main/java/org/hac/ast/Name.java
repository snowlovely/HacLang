package org.hac.ast;

import org.hac.core.Environment;
import org.hac.exception.HacException;
import org.hac.token.Token;

public class Name extends ASTLeaf {
    public Name(Token t) {
        super(t);
    }

    public String name() {
        return token().getText();
    }

    @Override
    public Object eval(Environment env) {
        Object value = env.get(name());
        if (value == null) {
            throw new HacException("undefined name: " + name(), this);
        } else {
            return value;
        }
    }
}
