package org.hac.parser;

import org.hac.ast.ASTree;
import org.hac.exception.ParseException;
import org.hac.lexer.Lexer;

import java.util.List;

public class Tree extends Element {
    protected Parser parser;

    protected Tree(Parser p) {
        parser = p;
    }

    protected void parse(Lexer lexer, List<ASTree> res)
            throws ParseException {
        res.add(parser.parse(lexer));
    }

    protected boolean match(Lexer lexer) throws ParseException {
        return parser.match(lexer);
    }
}