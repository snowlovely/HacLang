package org.hac.ast;

import org.hac.env.Environment;
import org.hac.token.Token;

public class NumberLiteral extends ASTLeaf {
    public NumberLiteral(Token t) {
        super(t);
    }

    public int value() {
        return token().getNumber();
    }

    @Override
    public Object eval(Environment env) {
        return this.value();
    }
}
