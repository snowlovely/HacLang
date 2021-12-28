package org.hac.core;

import org.hac.ast.Arguments;
import org.hac.ast.DefStmt;
import org.hac.ast.Fun;
import org.hac.ast.ParameterList;
import org.hac.parser.Parser;

import static org.hac.parser.Parser.rule;

public class FuncParser extends BasicParser {
    Parser param = rule().identifier(reserved);
    // params : param { ","  param }
    Parser params = rule(ParameterList.class)
            .ast(param).repeat(rule().sep(",").ast(param));
    // param_list : param "(" [ params ] ")"
    Parser paramList = rule().sep("(").maybe(params).sep(")");
    // def : "def" identifier param_list block
    Parser def = rule(DefStmt.class)
            .sep("def").identifier(reserved).ast(paramList).ast(block);
    // args : expr { "," expr }
    Parser args = rule(Arguments.class)
            .ast(expr).repeat(rule().sep(",").ast(expr));
    // postfix : "(" [ args ] ")"
    Parser postfix = rule().sep("(").maybe(args).sep(")");

    public FuncParser() {
        reserved.add(")");
        // primary : ( "(" expr ")" | number | identifier | string ) { postfix }
        primary.repeat(postfix);
        // simple : expr [args]
        simple.option(args);
        // program : [ def | statement ] ( ";" | EOL )
        program.insertChoice(def);
        // primary : ( "(" expr ")" | number | identifier | string | "fun" ) { postfix }
        primary.insertChoice(rule(Fun.class)
                .sep("fun").ast(paramList).ast(block));
    }
}