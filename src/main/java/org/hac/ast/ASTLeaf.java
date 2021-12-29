package org.hac.ast;

import org.hac.env.Environment;
import org.hac.exception.HacException;
import org.hac.token.Token;

import java.util.ArrayList;
import java.util.Iterator;

public class ASTLeaf extends ASTree {
    private static final ArrayList<ASTree> empty = new ArrayList<>();
    protected Token token;

    public ASTLeaf(Token t) {
        token = t;
    }

    @Override
    public ASTree child(int i) {
        throw new IndexOutOfBoundsException();
    }

    @Override
    public int numChildren() {
        return 0;
    }

    @Override
    public Iterator<ASTree> children() {
        return empty.iterator();
    }

    @Override
    public String toString() {
        return token.getText();
    }

    @Override
    public String location() {
        return "at line " + token.getLineNumber();
    }

    @Override
    public Object eval(Environment env) {
        throw new HacException("cannot eval: " + toString(), this);
    }

    public Token token() {
        return token;
    }
}