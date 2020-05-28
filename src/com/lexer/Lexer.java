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

    private StringBuilder buffer;

    private List<Token> tokens = new LinkedList<>();;

    private static final Logger log = Logger.getLogger(Lexer.class.getName());

    public Lexer(String filePath) throws IOException {
        readWholeFile(filePath);
        analyser();
    }

    private void readWholeFile(String filePath) throws IOException {
        File file = new File(filePath);
        FileInputStream fileInputStream = new FileInputStream(file);
        byte[] data = new byte[(int) file.length()];
        if(fileInputStream.read(data) != -1) {
            log.info("File " + filePath + " read correctly");
        }
        else{
            log.severe("File " + filePath + "  read incorrectly");
        }
        fileInputStream.close();
        wholeFile = new String(data, StandardCharsets.UTF_8);
        System.out.println(wholeFile);
    }

    private void analyser() {
        int numberOfCharacters = wholeFile.length();
        for(int i = 0; i < numberOfCharacters; i++) {
            char c = wholeFile.charAt(i);
            switch (state){
                case START: startState(c);
            }
        }
    }

    private void addCurrentCharacterToBuffer(char c, State state) {
        buffer.append(c);
        this.state = state;
    }

    private void addToken(TokenType tokenType, String value) {
        tokens.add(new Token(tokenType, value));
        log.info("Add token with type: " + tokenType.toString() + "  and value: " + value);
        buffer = new StringBuilder();
    }

    private void addToken(TokenType tokenType, char value) {
        tokens.add(new Token(tokenType, String.valueOf(value)));
        log.info("Add token with type: " + tokenType.toString() + " and byte value: " + (byte)value);
        buffer = new StringBuilder();
    }

    //buffer is empty
    private void startState(char c) {
        if(Character.isWhitespace(c)) {
            addToken(TokenType.WHITESPACE, c);
            state = State.START;
        } else if(c == '/') {
            addCurrentCharacterToBuffer(c, State.SINGLE_SLASH);
        }

    }



}
