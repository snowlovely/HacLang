package org.hac.ast;

import org.hac.core.Environment;
import org.hac.token.Token;

public class StringLiteral extends ASTLeaf {
    public StringLiteral(Token t) {
        super(t);
    }

    public String value() {
        return token().getText();
    }

    @Override
    public Object eval(Environment env) {
        return this.value();
    }
}
