package org.hac.parser;

import org.hac.ast.ASTList;
import org.hac.ast.ASTree;
import org.hac.exception.ParseException;
import org.hac.lexer.Lexer;

import java.util.List;

public class Repeat extends Element {
    protected Parser parser;
    protected boolean onlyOnce;

    protected Repeat(Parser p, boolean once) {
        parser = p;
        onlyOnce = once;
    }

    @Override
    protected void parse(Lexer lexer, List<ASTree> res) throws ParseException {
        while (parser.match(lexer)) {
            ASTree t = parser.parse(lexer);
            if (t.getClass() != ASTList.class || t.numChildren() > 0) {
                res.add(t);
            }
            if (onlyOnce) {
                break;
            }
        }
    }

    @Override
    protected boolean match(Lexer lexer) throws ParseException {
        return parser.match(lexer);
    }
}