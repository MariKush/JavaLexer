package com.lexer;


import javafx.util.Pair;

public class Token implements Comparable<Token>{
    private Pair<TokenType, String> token;

    public Token(TokenType tokenType, String value) {
        token = new Pair<>(tokenType, value);
    }

    public TokenType getTokenType() {
        return token.getKey();
    }

    public String getValue() {
        return token.getValue();
    }

    @Override
    public int compareTo(Token token) {
        return this.getTokenType().compareTo(token.getTokenType());
    }
}
