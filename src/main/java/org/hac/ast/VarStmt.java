package org.hac.ast;

import java.util.List;

public class VarStmt extends ASTList {
    public VarStmt(List<ASTree> c) {
        super(c);
    }

    public String name() {
        return ((ASTLeaf) child(0)).token().getText();
    }

    public TypeTag type() {
        return (TypeTag) child(1);
    }

    public ASTree initializer() {
        return child(2);
    }

    @Override
    public String toString() {
        return "(var " + name() + " " + type() + " " + initializer() + ")";
    }
}
