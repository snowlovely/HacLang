package org.hac.core;

import org.hac.ast.*;
import org.hac.exception.ParseException;
import org.hac.lexer.Lexer;
import org.hac.parser.Operators;
import org.hac.parser.Parser;
import org.hac.token.Token;

import java.util.HashSet;

import static org.hac.parser.Parser.rule;

public class CoreParser {
    HashSet<String> reserved = new HashSet<>();
    Operators operators = new Operators();
    Parser expr0 = Parser.rule();
    // primary : "(" expr ")" | number | identifier | string
    Parser primary = Parser.rule(PrimaryExpr.class)
            .or(Parser.rule().sep("(").ast(expr0).sep(")"),
                    Parser.rule().number(NumberLiteral.class),
                    Parser.rule().identifier(Name.class, reserved),
                    Parser.rule().string(StringLiteral.class));
    // factor : - primary | primary
    Parser factor = Parser.rule()
            .or(Parser.rule(NegativeExpr.class).sep("-").ast(primary), primary);
    // expr : factor { operator factor }
    Parser expr = expr0.expression(BinaryExpr.class, factor, operators);

    Parser statement0 = Parser.rule();
    // block: "{" [ statement ] { ( ";" | EOL ) [statement] } "}"
    Parser block = Parser.rule(BlockStmt.class)
            .sep("{").option(statement0)
            .repeat(Parser.rule().sep(";", Token.EOL).option(statement0))
            .sep("}");
    // simple: expr
    Parser simple = Parser.rule(PrimaryExpr.class).ast(expr);
    // statement : "if" expr block [ "else" block ] | "while" expr block | simple
    Parser statement = statement0.or(
            Parser.rule(IfStmt.class).sep("if").ast(expr).ast(block)
                    .option(Parser.rule().sep("else").ast(block)),
            Parser.rule(WhileStmt.class).sep("while").ast(expr).ast(block),
            simple);
    // program : [ statement ] ( ";" | EOL )
    // program : ( statement | null ) ( ";" | EOL )
    Parser program = Parser.rule().or(statement, Parser.rule(NullStmt.class))
            .sep(";", Token.EOL);
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
    //
    Parser elements = rule(ArrayLiteral.class)
            .ast(expr).repeat(rule().sep(",").ast(expr));

    public CoreParser() {
        reserved.add(";");
        reserved.add("}");
        reserved.add(Token.EOL);

        operators.add("=", 1, Operators.RIGHT);
        operators.add("==", 2, Operators.LEFT);
        operators.add(">", 2, Operators.LEFT);
        operators.add(">=", 2, Operators.LEFT);
        operators.add("<", 2, Operators.LEFT);
        operators.add("<=", 2, Operators.LEFT);
        operators.add("+", 3, Operators.LEFT);
        operators.add("-", 3, Operators.LEFT);
        operators.add("*", 4, Operators.LEFT);
        operators.add("/", 4, Operators.LEFT);
        operators.add("%", 4, Operators.LEFT);

        reserved.add("]");
        primary.insertChoice(rule().sep("[").maybe(elements).sep("]"));
        postfix.insertChoice(rule(ArrayRef.class).sep("[").ast(expr).sep("]"));
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

    public ASTree parse(Lexer lexer) throws ParseException {
        return program.parse(lexer);
    }
}
