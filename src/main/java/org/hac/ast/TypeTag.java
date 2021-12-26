package org.hac.ast;

import java.util.List;

public class TypeTag extends ASTList {
    public static final String UNDEF = "<Undef>";

    public TypeTag(List<ASTree> c) {
        super(c);
    }

    public String type() {
        if (numChildren() > 0) {
            return ((ASTLeaf) child(0)).token().getText();
        } else {
            return UNDEF;
        }
    }

    @Override
    public String toString() {
        return ":" + type();
    }
}
