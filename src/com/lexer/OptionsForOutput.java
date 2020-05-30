package com.lexer;

import java.util.*;

public class OptionsForOutput {

    public static void sequenceOfTokens(List<Token> tokenList) {
        System.out.println("------------ SEQUENCE OF TOKENS ------------");
        for (Token token : tokenList) {
            if (token.getTokenType() != TokenType.WHITE_SPACE)
                System.out.println("TokenName: " + token.getTokenType() + "\n\tvalue: " + token.getValue()+ "\n");
        }
    }

    public static void tokensSortedByType(List<Token> tokenList){
        System.out.println("------------ TOKENS SORTED BY TYPE ------------");
        List<Token> sortedTokensList = new ArrayList<>(tokenList);
        sortedTokensList.sort(Token::compareTo);
        int countTokens = sortedTokensList.size();
        int index = 0;
        TokenType currentTokenType = sortedTokensList.get(0).getTokenType();
        System.out.println("TokenName: " + currentTokenType);
        while (index < countTokens){
            if(sortedTokensList.get(index).getTokenType() == TokenType.WHITE_SPACE){
                index++;
                continue;
            }
            if(currentTokenType != sortedTokensList.get(index).getTokenType()){
                currentTokenType = sortedTokensList.get(index).getTokenType();
                System.out.println("\nTokenName: " + currentTokenType);
            }
            System.out.println("\tvalue: " + sortedTokensList.get(index).getValue());
            index++;
        }
    }




}
