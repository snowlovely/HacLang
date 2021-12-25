package org.hac.parser;

import org.hac.ast.ASTree;
import org.hac.exception.ParseException;
import org.hac.lexer.Lexer;

import java.util.List;

public class OrTree extends Element {
        protected Parser[] parsers;
        protected OrTree(Parser[] p) { parsers = p; }
        protected void parse(Lexer lexer, List<ASTree> res)
            throws ParseException
        {
            Parser p = choose(lexer);
            if (p == null)
                throw new ParseException(lexer.peek(0));
            else
                res.add(p.parse(lexer));
        }
        protected boolean match(Lexer lexer) throws ParseException {
            return choose(lexer) != null;
        }
        protected Parser choose(Lexer lexer) throws ParseException {
            for (Parser p: parsers)
                if (p.match(lexer))
                    return p;

            return null;
        }
        protected void insert(Parser p) {
            Parser[] newParsers = new Parser[parsers.length + 1];
            newParsers[0] = p;
            System.arraycopy(parsers, 0, newParsers, 1, parsers.length);
            parsers = newParsers;
        }
    }