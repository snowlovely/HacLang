package org.hac.lexer;

import java.io.Reader;

public class Lexer {
    private final Reader reader;
    private static final int EMPTY = -1;
    private int lastChar = EMPTY;

    public Lexer(Reader r) {
        this.reader = r;
    }

    private int getChar() throws Exception {
        if (lastChar == EMPTY) {
            return reader.read();
        } else {
            int c = lastChar;
            lastChar = EMPTY;
            return c;
        }
    }

    @SuppressWarnings("all")
    private void ungetChar(int c) {
        lastChar = c;
    }

    private static boolean isLetter(int c) {
        return ('A' <= c && c <= 'Z') || ('a' <= c && c <= 'z');
    }

    private static boolean isDigit(int c) {
        return '0' <= c && c <= '9';
    }

    private static boolean isSpace(int c) {
        return 0 <= c && c <= ' ';
    }

    public String read() throws Exception {
        StringBuilder sb = new StringBuilder();
        int c = getChar();
        while (isSpace(c)) {
            c = getChar();
        }
        if (c < 0) {
            return null;
        } else if (isDigit(c)) {
            sb.append((char) c);
            c = getChar();
            while (isDigit(c)) {
                sb.append((char) c);
                c = getChar();
            }
        } else if (isLetter(c)) {
            sb.append((char) c);
            c = getChar();
            while (isLetter(c) || isDigit(c)) {
                sb.append((char) c);
                c = getChar();
            }
        } else if (c == '=') {
            c = getChar();
            if (c == '=') {
                return "==";
            } else {
                ungetChar(c);
                return "=";
            }
        } else {
            throw new Exception();
        }
        if (c >= 0) {
            ungetChar(c);
        }
        return sb.toString();
    }
}
