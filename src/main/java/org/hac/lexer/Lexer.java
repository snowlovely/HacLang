package org.hac.lexer;

import org.hac.exception.ParseException;
import org.hac.token.IdToken;
import org.hac.token.NumToken;
import org.hac.token.StrToken;
import org.hac.token.Token;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;

public class Lexer {
    private final Reader reader;
    private static final int EMPTY = -1;
    private int lastChar = EMPTY;

    private final ArrayList<Token> queue = new ArrayList<>();
    private static int LINE_NUMBER = 1;

    public Lexer(Reader r) {
        this.reader = r;
    }

    public Token read() throws ParseException {
        if (fillQueue(0)) {
            return queue.remove(0);
        } else {
            return Token.EOF;
        }
    }

    public Token peek(int i) throws ParseException {
        if (fillQueue(i)) {
            return queue.get(i);
        } else {
            return Token.EOF;
        }
    }

    private boolean fillQueue(int i) throws ParseException {
        while (queue.size() <= i) {
            Token token = read0();
            if (token == Token.EOF) {
                return false;
            }
            queue.add(token);
        }
        return true;
    }

    private int getChar() throws IOException {
        if (lastChar == EMPTY) {
            return reader.read();
        } else {
            int c = lastChar;
            lastChar = EMPTY;
            return c;
        }
    }

    private static boolean isLetter(int c) {
        return ('A' <= c && c <= 'Z') || ('a' <= c && c <= 'z');
    }

    private static boolean isDigit(int c) {
        return '0' <= c && c <= '9';
    }

    private static boolean isSpace(int c) {
        return c == ' ' || c == '\t';
    }

    private static boolean isCR(int c) {
        return c == '\r';
    }

    private static boolean isLF(int c) {
        return c == '\n';
    }

    private static boolean isCalc(int c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '%';
    }

    private static boolean isBracket(int c) {
        return c == '{' || c == '}';
    }

    private static boolean isSem(int c) {
        return c == ';';
    }

    @SuppressWarnings("all")
    private void ungetChar(int c) {
        lastChar = c;
    }

    public Token read0() throws ParseException {
        try {
            StringBuilder sb = new StringBuilder();
            int c = getChar();
            while (isSpace(c)) {
                c = getChar();
            }
            if (c < 0) {
                return Token.EOF;
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
            } else if (c == '"') {
                sb.append((char) c);
                c = getChar();
                while (c != '"') {
                    sb.append((char) c);
                    c = getChar();
                }
                c = getChar();
            } else if (isCR(c)) {
                c = getChar();
                if (isLF(c)) {
                    LINE_NUMBER++;
                    return new IdToken(LINE_NUMBER - 1, Token.EOL);
                } else {
                    throw new ParseException("error token");
                }
            } else if (isLF(c)) {
                LINE_NUMBER++;
                return new IdToken(LINE_NUMBER - 1, Token.EOL);
            } else if (c == '=') {
                c = getChar();
                if (c == '=') {
                    return new IdToken(LINE_NUMBER, "==");
                } else {
                    ungetChar(c);
                    return new IdToken(LINE_NUMBER, "=");
                }
            } else if (c == '>') {
                c = getChar();
                if (c == '=') {
                    return new IdToken(LINE_NUMBER, ">=");
                } else {
                    ungetChar(c);
                    return new IdToken(LINE_NUMBER, ">");
                }
            } else if (c == '<') {
                c = getChar();
                if (c == '=') {
                    return new IdToken(LINE_NUMBER, "<=");
                } else {
                    ungetChar(c);
                    return new IdToken(LINE_NUMBER, "<");
                }
            } else if (isCalc(c)) {
                return new IdToken(LINE_NUMBER, String.valueOf((char) c));
            } else if (isBracket(c)) {
                return new IdToken(LINE_NUMBER, String.valueOf((char) c));
            } else if (isSem(c)) {
                return new IdToken(LINE_NUMBER, ";");
            } else {
                throw new ParseException("error token");
            }
            if (c >= 0) {
                ungetChar(c);
            }
            String temp = sb.toString();
            if (isLetter(temp.toCharArray()[0])) {
                return new IdToken(LINE_NUMBER, temp);
            } else if (isDigit(temp.toCharArray()[0])) {
                return new NumToken(LINE_NUMBER, Integer.parseInt(temp));
            } else if (temp.toCharArray()[0] == '"') {
                temp = temp.substring(1);
                return new StrToken(LINE_NUMBER, temp);
            } else {
                throw new ParseException("error token");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Token.EOF;
    }
}
