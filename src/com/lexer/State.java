package com.lexer;

public enum State {
    ERROR,
    START,
    SINGLE_SLASH,
    SINGLE_LINE_COMMENT,
    MULTI_LINE_COMMENT,
    MULTI_LINE_COMMENT_AND_STAR,
    SINGLE_PLUS,
    SINGLE_MINUS
}
