package com.lexer;

import java.util.Arrays;
import java.util.HashSet;

public class SymbolType {

    public static final HashSet<String> keywords = new HashSet<>(Arrays.asList("abstract", "assert", "boolean", "break", "byte",
            "case", "catch", "char", "class", "const", "continue", "default", "do", "double", "else", "finally",
            "enum", "extends", "final", "float", "for", "if", "goto", "implements", "import", "instanceof", "int", "interface",
            "long", "native", "new", "package", "private", "protected", "public", "return", "short", "static", "strictfp",
            "super", "switch", "synchronized", "this", "throw", "throws", "transient", "try", "void", "volatile", "while"));

    public static boolean isKeyword(String word)
    {
        return keywords.contains(word);
    }



}
