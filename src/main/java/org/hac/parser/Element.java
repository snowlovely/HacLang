package org.hac.parser;

import org.hac.ast.ASTree;
import org.hac.exception.ParseException;
import org.hac.lexer.Lexer;

import java.util.List;

public abstract class Element {
    protected abstract void parse(Lexer lexer, List<ASTree> res) throws ParseException;

    protected abstract boolean match(Lexer lexer) throws ParseException;
}