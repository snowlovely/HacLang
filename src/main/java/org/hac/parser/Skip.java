package org.hac.parser;

import org.hac.ast.ASTree;
import org.hac.token.Token;

import java.util.List;

public class Skip extends Leaf {
    protected Skip(String[] t) {
        super(t);
    }

    @Override
    protected void find(List<ASTree> res, Token t) {
    }
}