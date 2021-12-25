package org.hac.parser;

import org.hac.ast.ASTLeaf;
import org.hac.token.Token;

public class StrToken extends AToken {
    protected StrToken(Class<? extends ASTLeaf> type) {
        super(type);
    }

    protected boolean test(Token t) {
        return t.isString();
    }
}