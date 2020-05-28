package com.lexer;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

public class Lexer {

    private String wholeFile;

    private State state = State.START;

    private StringBuilder buffer = new StringBuilder();

    private List<Token> tokens = new LinkedList<>();

    private int currentIndex;

    public Lexer(String filePath) throws IOException {
        readWholeFile(filePath);
        analyser();
    }

    private void readWholeFile(String filePath) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(new File(filePath));
        byte[] data = fileInputStream.readAllBytes();
        fileInputStream.close();
        wholeFile = new String(data, StandardCharsets.UTF_8).replace("\r", "");
    }

    private void analyser() {
        int numberOfCharacters = wholeFile.length();
        for (currentIndex = 0; currentIndex < numberOfCharacters; currentIndex++) {
            char c = wholeFile.charAt(currentIndex);
            switch (state) {
                case START:
                    startState(c);
                    break;
                case SINGLE_SLASH:
                    singleSlashState(c);
                    break;
                case SINGLE_LINE_COMMENT:
                    singleLineCommentState(c);
                    break;
                case MULTI_LINE_COMMENT:
                    multiLineCommentState(c);
                    break;
                case MULTI_LINE_COMMENT_AND_STAR:
                    multiLineCommentAndStarState(c);
                    break;
                case SINGLE_PLUS:
                    singlePlusState(c);
                    break;
                case SINGLE_MINUS:
                    singleMinusState(c);
                    break;
            }
        }
    }


    private void addCharacterToBuffer(char c) {
        buffer.append(c);
    }

    private void addCharacterToBuffer(char c, State state) {
        buffer.append(c);
        this.state = state;
    }

    private void addToken(TokenType tokenType, String value) {
        tokens.add(new Token(tokenType, value));
        System.out.println("Add token with type: " + tokenType.toString() + " and value: " + value);
    }

    private void addToken(TokenType tokenType) {
        addToken(tokenType, buffer.toString());
        buffer = new StringBuilder();
    }

    private void addToken(TokenType tokenType, char value) {
        tokens.add(new Token(tokenType, String.valueOf(value)));
        System.out.println("Add token with type: " + tokenType.toString() + " and byte value: " + (byte) value);
    }

    //buffer is empty
    private void startState(char c) {
        if (Character.isWhitespace(c)) {
            addToken(TokenType.WHITE_SPACE, c);
        } else if (c == '/') {
            addCharacterToBuffer(c, State.SINGLE_SLASH);
        } else if (c == '+') {
            addCharacterToBuffer(c, State.SINGLE_PLUS);
        } else if (c == '-') {
            addCharacterToBuffer(c, State.SINGLE_PLUS);
        }

    }

    //possible states: //, /*, /=
    private void singleSlashState(char c) {
        if (c == '/') {
            addCharacterToBuffer(c, State.SINGLE_LINE_COMMENT);
        } else if (c == '*') {
            addCharacterToBuffer(c, State.MULTI_LINE_COMMENT);
        } else if (c == '=') {
            addCharacterToBuffer(c, State.START);
            addToken(TokenType.OPERATOR);
        } else {
            addToken(TokenType.OPERATOR);
            currentIndex--;
            state = State.START;
        }
    }

    private void singleLineCommentState(char c) {
        if (c == '\n') {
            addToken(TokenType.COMMENT);
            addToken(TokenType.WHITE_SPACE, c);
            state = State.START;
        } else {
            addCharacterToBuffer(c);
        }

    }

    private void multiLineCommentState(char c) {
        if (c == '*') {
            addCharacterToBuffer(c, State.MULTI_LINE_COMMENT_AND_STAR);
        } else {
            addCharacterToBuffer(c);
        }
    }

    private void multiLineCommentAndStarState(char c) {
        if (c == '/') {
            addCharacterToBuffer(c, State.START);
            addToken(TokenType.COMMENT);
        } else if (c == '*') {
            addCharacterToBuffer(c);
        } else {
            addCharacterToBuffer(c, State.MULTI_LINE_COMMENT);
        }
    }

    private void singlePlusState(char c) {
        if (c == '+' || c == '=') {
            addCharacterToBuffer(c, State.START);
            addToken(TokenType.OPERATOR);
        } else {
            addToken(TokenType.OPERATOR);
            currentIndex--;
            state = State.START;
        }
    }

    private void singleMinusState(char c) {
        if (c == '-' || c == '=') {
            addCharacterToBuffer(c, State.START);
            addToken(TokenType.OPERATOR);
        } else {
            addToken(TokenType.OPERATOR);
            currentIndex--;
            state = State.START;
        }
    }
}
