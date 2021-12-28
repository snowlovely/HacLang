package org.hac.ast;

import org.hac.core.Environment;
import org.hac.exception.HacException;

import java.util.List;

import static org.hac.core.Environment.FALSE;
import static org.hac.core.Environment.TRUE;

public class BinaryExpr extends ASTList {
    public BinaryExpr(List<ASTree> c) {
        super(c);
    }

    public ASTree left() {
        return child(0);
    }

    public String operator() {
        return ((ASTLeaf) child(1)).token().getText();
    }

    public ASTree right() {
        return child(2);
    }

    @Override
    public Object eval(Environment env) {
        String op = operator();
        if ("=".equals(op)) {
            Object right = right().eval(env);
            return computeAssign(env, right);
        } else {
            Object left = left().eval(env);
            Object right = right().eval(env);
            return computeOp(left, op, right);
        }
    }

    private Object computeAssign(Environment env, Object rvalue) {
        ASTree l = left();
        if (l instanceof Name) {
            env.put(((Name) l).name(), rvalue);
            return rvalue;
        } else {
            throw new HacException("bad assignment", this);
        }
    }

    @SuppressWarnings("all")
    private Object computeOp(Object left, String op, Object right) {
        if (left instanceof Integer && right instanceof Integer) {
            return computeNumber((Integer) left, op, (Integer) right);
        } else if (op.equals("+")) {
            return String.valueOf(left) + String.valueOf(right);
        } else if (op.equals("==")) {
            if (left == null) {
                return right == null ? TRUE : FALSE;
            } else {
                return left.equals(right) ? TRUE : FALSE;
            }
        } else {
            throw new HacException("bad type", this);
        }
    }

    private Object computeNumber(Integer left, String op, Integer right) {
        int a = left;
        int b = right;
        switch (op) {
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "*":
                return a * b;
            case "/":
                return a / b;
            case "%":
                return a % b;
            case "==":
                return a == b ? TRUE : FALSE;
            case ">":
                return a > b ? TRUE : FALSE;
            case ">=":
                return a >= b ? TRUE : FALSE;
            case "<":
                return a < b ? TRUE : FALSE;
            case "<=":
                return a <= b ? TRUE : FALSE;
            default:
                throw new HacException("bad operator", this);
        }
    }
}
