package com.lexer;


import javafx.util.Pair;

public class Token {
    private Pair<TokenType, String> token;

    public Token(TokenType tokenName, String value) {
        token = new Pair<>(tokenName, value);
    }

    public TokenType getTokenName() {
        return token.getKey();
    }

    public String getValue() {
        return token.getValue();
    }
}
