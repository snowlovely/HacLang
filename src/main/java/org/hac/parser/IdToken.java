package org.hac.parser;

import org.hac.ast.ASTLeaf;
import org.hac.token.Token;

import java.util.HashSet;

public class IdToken extends AToken {
    HashSet<String> reserved;

    protected IdToken(Class<? extends ASTLeaf> type, HashSet<String> r) {
        super(type);
        reserved = r != null ? r : new HashSet<>();
    }

    @Override
    protected boolean test(Token t) {
        return t.isIdentifier() && !reserved.contains(t.getText());
    }
}