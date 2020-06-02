package com.lexer;

import java.util.ArrayList;
import java.util.List;

public class OptionsForOutput {

    public static void sequenceOfTokens(List<Token> tokenList) {
        System.out.println("------------ SEQUENCE OF TOKENS ------------");
        for (Token token : tokenList) {
            if (token.getTokenType() != TokenType.WHITE_SPACE)
                System.out.println("TokenName: " + token.getTokenType() + "\n\tvalue: " + token.getValue() + "\n");
        }
    }

    public static void sourceCodeHighlighting(List<Token> tokenList) {
        String ANSI_RESET = "\u001B[0m";
        String ANSI_BLACK = "\u001B[30m";
        String ANSI_RED = "\u001B[31m";
        String ANSI_GREEN = "\u001B[32m";
        String ANSI_YELLOW = "\u001B[33m";
        String ANSI_BLUE = "\u001B[34m";
        String ANSI_PURPLE = "\u001B[35m";
        String ANSI_CYAN = "\u001B[36m";
        String ANSI_WHITE = "\u001B[37m";

        System.out.println("------------ SEQUENCE CODE HIGHLIGHTING ------------");
        for (Token token : tokenList) {
            switch (token.getTokenType()) {
                case ERROR:
                    System.out.print(ANSI_RED + token.getValue());
                    break;
                case WHITE_SPACE:
                    System.out.print(token.getValue());
                    break;
                case NUMERIC:
                    System.out.print(ANSI_BLUE + token.getValue());
                    break;
                case LITERAL:
                case SYMBOLIC:
                case BOOLEAN_LITERAL:
                case NULL_LITERAL:
                    System.out.print(ANSI_GREEN + token.getValue());
                    break;
                case SINGLE_LINE_COMMENT:
                case MULTI_LINE_COMMENT:
                    System.out.print(ANSI_WHITE + token.getValue());
                    break;
                case KEYWORD:
                    System.out.print(ANSI_YELLOW + token.getValue());
                    break;
                case IDENTIFIER:
                    System.out.print(ANSI_BLACK + token.getValue());
                    break;
                case OPERATOR:
                    System.out.print(ANSI_PURPLE + token.getValue());
                    break;
                case PUNCTUATION:
                    System.out.print(ANSI_CYAN + token.getValue());
                    break;
            }

        }
        System.out.println(ANSI_RESET);
    }


    public static void tokensSortedByType(List<Token> tokenList) {
        System.out.println("------------ TOKENS SORTED BY TYPE ------------");
        List<Token> sortedTokensList = new ArrayList<>(tokenList);
        sortedTokensList.sort(Token::compareTo);
        int countTokens = sortedTokensList.size();
        int index = 0;
        TokenType currentTokenType = sortedTokensList.get(0).getTokenType();
        if (currentTokenType != TokenType.WHITE_SPACE)
            System.out.println("TokenName: " + currentTokenType);
        while (index < countTokens) {
            if (sortedTokensList.get(index).getTokenType() == TokenType.WHITE_SPACE) {
                index++;
                continue;
            }
            if (currentTokenType != sortedTokensList.get(index).getTokenType()) {
                currentTokenType = sortedTokensList.get(index).getTokenType();
                System.out.println("\nTokenName: " + currentTokenType);
            }
            System.out.println("\tvalue: " + sortedTokensList.get(index).getValue());
            index++;
        }
    }

}
