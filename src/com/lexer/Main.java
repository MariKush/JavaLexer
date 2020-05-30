package com.lexer;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        new Lexer("chown.java");
        new Lexer("file.java");
    }
}
