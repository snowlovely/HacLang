package org.hac.ast;

import org.hac.core.Environment;
import org.hac.core.Symbols;
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

    protected Object computeAssign0(Environment env, Object rvalue) {
        ASTree le = left();
        if (le instanceof PrimaryExpr) {
            if (((PrimaryExpr) le).hasPostfix(0) &&
                    ((PrimaryExpr) le).postfix(0) instanceof ArrayRef) {
                Object a = ((PrimaryExpr) le).evalSubExpr(env, 1);
                if (a instanceof Object[]) {
                    ArrayRef ref = (ArrayRef) ((PrimaryExpr) le).postfix(0);
                    Object index = ref.index().eval(env);
                    if (index instanceof Integer) {
                        ((Object[]) a)[(Integer) index] = rvalue;
                        return rvalue;
                    }
                }
                throw new HacException("bad array access", this);
            }
        }
        if (le instanceof Name) {
            env.put(((Name) le).name(), rvalue);
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

    @Override
    public void lookup(Symbols sym) {
        ASTree left = left();
        if ("=".equals(operator())) {
            if (left instanceof Name) {
                ((Name) left).lookupForAssign(sym);
                right().lookup(sym);
                return;
            }
        }
        left.lookup(sym);
        right().lookup(sym);
    }

    protected Object computeAssign(Environment env, Object rvalue) {
        ASTree l = left();
        if (l instanceof Name) {
            ((Name) l).evalForAssign(env, rvalue);
            return rvalue;
        } else {
            return computeAssign0(env, rvalue);
        }
    }
}
