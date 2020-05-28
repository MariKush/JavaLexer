package com.lexer;

public enum State {
    ERROR,
    START,
    SINGLE_SLASH,
    SINGLE_LINE_COMMENT,
    MULTI_LINE_COMMENT,
    MULTI_LINE_COMMENT_AND_STAR,
    SINGLE_PLUS,
    SINGLE_MINUS,
    OPERATOR_MAYBE_BEFORE_EQUAL,//^, %, !, =, *, <<, >>>
    SINGLE_LESS_THAN,
    SINGLE_GREATER_THAN,
    DOUBLE_GREATER_THAN,
    SINGLE_COLON,
    SINGLE_AMPERSAND,
    SINGLE_VERTICAL_BAR,
    SINGLE_DOT,
    DOUBLE_DOT

}
