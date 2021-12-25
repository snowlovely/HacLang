package org.hac.core;

import org.hac.ast.ASTree;

public class HacException extends RuntimeException {
    public HacException(String m) {
        super(m);
    }

    public HacException(String m, ASTree t) {
        super(m + " " + t.location());
    }
}
