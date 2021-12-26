package org.hac.parser;

import org.hac.ast.ASTLeaf;
import org.hac.token.Token;

public class NumToken extends AToken {
    protected NumToken(Class<? extends ASTLeaf> type) {
        super(type);
    }

    @Override
    protected boolean test(Token t) {
        return t.isNumber();
    }
}