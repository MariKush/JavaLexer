//dgsgqhegyudwyu ga eqvfs///s\r\n
//qwe*qwe*/*
/* ieie //
`a**/
a = b
c == eg
f -= f

=   >   <   !      ->
==  >=  <=  !=   ++  --
+   -   *   /    ^   %   <<   >>   >>>
+=  -=  *=  /=   ^=  %=  <<=  >>=  >>>=


:

~   ?

&&  ||
&   |
&=  |=
        (   )   {   }   [   ]   ;   ,   .   ...   @   :: ..


masha; rjhj
13kgd
kjk34354h
oRDdw_jjjd
public static void main(String[]args)

0
142132
237816L
0x7fff_ffff
0177_7777_7777
0b0111_1111_1111_1111_1111_1111_1111_1111
7687d214 6312865F
673687.9167
1221_1333.4443
0.6674287
.967556

'1' '\123' '\7' '\12' 'qs'
        '\1234567'
'\r'
'w'
        '""'

'eeq
'qwe\'aad'
'\'
        "asdsac"
"dfghju65\\bhccfcf\"


        "dfghju65\123bhccfcf"
        "ikxik"

"\\"

""

        package com.lexer;

        import java.io.File;
        import java.io.FileInputStream;
        import java.io.IOException;
        import java.nio.charset.StandardCharsets;
        import java.util.LinkedList;
        import java.util.List;

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
        wholeFile += "\n";
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
                case OPERATOR_MAYBE_BEFORE_EQUAL:
                    operatorMaybeBeforeEqualState(c);
                    break;
                case SINGLE_LESS_THAN:
                    singleLessThanState(c);
                    break;
                case SINGLE_GREATER_THAN:
                    singleGreaterThanState(c);
                    break;
                case DOUBLE_GREATER_THAN:
                    doubleGreaterThanState(c);
                    break;
                case SINGLE_COLON:
                    singleColonState(c);
                    break;
                case SINGLE_AMPERSAND:
                    singleAmpersandState(c);
                    break;
                case SINGLE_VERTICAL_BAR:
                    singleVerticalBarState(c);
                    break;
                case SINGLE_DOT:
                    singleDotState(c);
                    break;
                case DOUBLE_DOT:
                    doubleDotState(c);
                    break;
                case IDENTIFIER:
                    identifierState(c);
                    break;
                case DECIMAL_NUMBER:
                    decimalNumberState(c);
                    break;
                case OCTAL_NUMBER:
                    octalNumberState(c);
                    break;
                case HEX_NUMBER:
                    hexNumberState(c);
                    break;
                case FLOATING_POINT_NUMBER:
                    floatingPointNumberState(c);
                    break;
                case SINGLE_ZERO:
                    singleZeroState(c);
                    break;
                case BINARY_NUMBER:
                    binaryNumberState(c);
                    break;
                case SYMBOLIC_CONSTANT:
                    symbolicConstantState(c);
                    break;
                case BACKSLASH_IN_SYMBOLIC_CONSTANT:
                    backslashInSymbolicConstantState(c);
                    break;
                case ONE_OCTAL_DIGIT_AFTER_BACKSLASH_IN_SYMBOLIC_CONSTANT:
                    oneOctalDigitAfterBackslashInSymbolicConstantState(c);
                    break;
                case TWO_OCTAL_DIGIT_AFTER_BACKSLASH_IN_SYMBOLIC_CONSTANT:
                    twoOctalDigitAfterBackslashInSymbolicConstantState(c);
                    break;
                case END_OF_SYMBOLIC_CONSTANT:
                    endOfSymbolicConstantState(c);
                    break;
                case ERROR_READ_SYMBOLIC_CONSTANT:
                    errorReadSymbolicConstantState(c);
                    break;
                case BACKSLASH_INSIDE_ERROR_READ_SYMBOLIC_CONSTANT:
                    backslashInsideErrorReadSymbolicConstantState(c);
                    break;
                case LITERAL_CONSTANT:
                    literalConstantState(c);
                    break;
                case BACKSLASH_IN_LITERAL_CONSTANT:
                    backslashInLiteralConstantState(c);
                    break;
                case ERROR_READ_LITERAL_CONSTANT:
                    errorReadLiteralConstantState(c);
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
        //System.out.println("Add token with type: " + tokenType.toString() + " and byte value: " + (byte) value);
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
            addCharacterToBuffer(c, State.SINGLE_MINUS);
        } else if (c == '=' || c == '^' || c == '%' || c == '!' || c == '*') {//^, %, !, =, *
            addCharacterToBuffer(c, State.OPERATOR_MAYBE_BEFORE_EQUAL);
        } else if (c == '<') {
            addCharacterToBuffer(c, State.SINGLE_LESS_THAN);
        } else if (c == '>') {
            addCharacterToBuffer(c, State.SINGLE_GREATER_THAN);
        } else if (c == '?' || c == '~') {
            addCharacterToBuffer(c, State.START);
            addToken(TokenType.OPERATOR);
        } else if (c == ':') {
            addCharacterToBuffer(c, State.SINGLE_COLON);
        } else if (c == '&') {
            addCharacterToBuffer(c, State.SINGLE_AMPERSAND);
        } else if (c == '|') {
            addCharacterToBuffer(c, State.SINGLE_VERTICAL_BAR);
        } else if (c == '(' || c == ')' || c == '{' || c == '}' || c == '[' || c == ']'
                || c == ';' || c == ',' || c == '@') { //( ) { } [ ] ; , @
            addCharacterToBuffer(c, State.START);
            addToken(TokenType.PUNCTUATION);
        } else if (c == '.') {
            addCharacterToBuffer(c, State.SINGLE_DOT);
        } else if (Character.isLetter(c) || c == '_' || c == '$') {
            addCharacterToBuffer(c, State.IDENTIFIER);
        } else if (c == '0') {
            addCharacterToBuffer(c, State.SINGLE_ZERO);
        } else if (c >= '1' && c <= '9') {
            addCharacterToBuffer(c, State.DECIMAL_NUMBER);
        } else if (c == '\'') {
            addCharacterToBuffer(c, State.SYMBOLIC_CONSTANT);
        } else if (c == '\"') {
            addCharacterToBuffer(c, State.LITERAL_CONSTANT);
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
        if (c == '+' || c == '=') {// ++ +=
            addCharacterToBuffer(c, State.START);
            addToken(TokenType.OPERATOR);
        } else {
            addToken(TokenType.OPERATOR);
            currentIndex--;
            state = State.START;
        }
    }

    private void singleMinusState(char c) {
        if (c == '-' || c == '=' || c == '>') {//-- -= ->
            addCharacterToBuffer(c, State.START);
            addToken(TokenType.OPERATOR);
        } else {//-
            addToken(TokenType.OPERATOR);
            currentIndex--;
            state = State.START;
        }
    }

    private void operatorMaybeBeforeEqualState(char c) {
        if (c == '=') {//==
            addCharacterToBuffer(c, State.START);
            addToken(TokenType.OPERATOR);
        } else {//=
            addToken(TokenType.OPERATOR);
            currentIndex--;
            state = State.START;
        }
    }

    private void singleLessThanState(char c) {
        if (c == '<') {//<<
            addCharacterToBuffer(c, State.OPERATOR_MAYBE_BEFORE_EQUAL);
        } else if (c == '=') {//<=
            addCharacterToBuffer(c, State.START);
            addToken(TokenType.OPERATOR);
        } else {//<
            addToken(TokenType.OPERATOR);
            currentIndex--;
            state = State.START;
        }
    }

    private void singleGreaterThanState(char c) {
        if (c == '>') {//>>
            addCharacterToBuffer(c, State.DOUBLE_GREATER_THAN);
        } else if (c == '=') {//>=
            addCharacterToBuffer(c, State.START);
            addToken(TokenType.OPERATOR);
        } else {//>
            addToken(TokenType.OPERATOR);
            currentIndex--;
            state = State.START;
        }
    }

    private void doubleGreaterThanState(char c) {
        if (c == '>') {//>>>
            addCharacterToBuffer(c, State.OPERATOR_MAYBE_BEFORE_EQUAL);
        } else if (c == '=') {//>>=
            addCharacterToBuffer(c, State.START);
            addToken(TokenType.OPERATOR);
        } else {//>>
            addToken(TokenType.OPERATOR);
            currentIndex--;
            state = State.START;
        }
    }

    private void singleColonState(char c) {
        if (c == ':') {// ::
            addCharacterToBuffer(c, State.START);
            addToken(TokenType.PUNCTUATION);
        } else {
            addToken(TokenType.OPERATOR);
            currentIndex--;
            state = State.START;
        }
    }

    private void singleAmpersandState(char c) {
        if (c == '&' || c == '=') {// && &=
            addCharacterToBuffer(c, State.START);
            addToken(TokenType.OPERATOR);
        } else {
            addToken(TokenType.OPERATOR);
            currentIndex--;
            state = State.START;
        }
    }

    private void singleVerticalBarState(char c) {
        if (c == '|' || c == '=') {// || |=
            addCharacterToBuffer(c, State.START);
            addToken(TokenType.OPERATOR);
        } else {
            addToken(TokenType.OPERATOR);
            currentIndex--;
            state = State.START;
        }
    }

    private void singleDotState(char c) {
        if (c == '.') {//..
            addCharacterToBuffer(c, State.DOUBLE_DOT);
        } else if (Character.isDigit(c)) {
            addCharacterToBuffer(c, State.FLOATING_POINT_NUMBER);
        } else {//.
            addToken(TokenType.PUNCTUATION);
            currentIndex--;
            state = State.START;
        }
    }

    private void doubleDotState(char c) {
        if (c == '.') {//...
            addCharacterToBuffer(c, State.START);
            addToken(TokenType.PUNCTUATION);
        } else {//..
            addToken(TokenType.ERROR);
            currentIndex--;
            state = State.START;
        }
    }

    private void identifierState(char c) {
        if (Character.isDigit(c) || Character.isLetter(c) || c == '_' || c == '$') {
            addCharacterToBuffer(c);
        } else if (SymbolType.isKeyword(buffer.toString())) {
            addToken(TokenType.KEYWORD);
            currentIndex--;
            state = State.START;
        } else {
            addToken(TokenType.IDENTIFIER);
            currentIndex--;
            state = State.START;
        }
    }

    private void decimalNumberState(char c) {
        if (Character.isDigit(c) || c == '_' || c == 'e' || c == 'E') {
            addCharacterToBuffer(c);
        } else if (c == '.') {
            addCharacterToBuffer(c, State.FLOATING_POINT_NUMBER);
        } else if (c == 'l' || c == 'L' || c == 'd' || c == 'D' || c == 'f' || c == 'F') {
            addCharacterToBuffer(c, State.START);
            addToken(TokenType.NUMERIC);
        } else {
            addToken(TokenType.NUMERIC);
            currentIndex--;
            state = State.START;
        }
    }

    private void singleZeroState(char c) {
        if (c == '.') {
            addCharacterToBuffer(c, State.FLOATING_POINT_NUMBER);
        } else if (c == 'x' || c == 'X') {
            addCharacterToBuffer(c, State.HEX_NUMBER);
        } else if (c >= '0' && c <= '7' || c == '_') {
            addCharacterToBuffer(c, State.OCTAL_NUMBER);
        } else if (c == 'b' || c == 'B') {
            addCharacterToBuffer(c, State.BINARY_NUMBER);
        } else {//0
            addToken(TokenType.NUMERIC);
            currentIndex--;
            state = State.START;
        }
    }

    private void octalNumberState(char c) {
        if (c >= '0' && c <= '7' || c == '_') {
            addCharacterToBuffer(c);
        } else if (c == 'l' || c == 'L') {
            addCharacterToBuffer(c, State.START);
            addToken(TokenType.NUMERIC);
        } else {
            addToken(TokenType.NUMERIC);
            currentIndex--;
            state = State.START;
        }
    }

    private void hexNumberState(char c) {
        if (Character.isDigit(c) || (c >= 'a' && c <= 'f') || (c >= 'A' && c <= 'F') || c == '_') {
            addCharacterToBuffer(c);
        } else {
            addToken(TokenType.NUMERIC);
            currentIndex--;
            state = State.START;
        }
    }

    private void floatingPointNumberState(char c) {
        if (Character.isDigit(c) || c == '_' || c == 'e' || c == 'E') {
            addCharacterToBuffer(c);
        } else if (c == 'd' || c == 'D' || c == 'f' || c == 'F') {
            addCharacterToBuffer(c, State.START);
            addToken(TokenType.NUMERIC);
        } else {
            addToken(TokenType.NUMERIC);
            currentIndex--;
            state = State.START;
        }
    }

    private void binaryNumberState(char c) {
        if (c == '0' || c == '1' || c == '_') {
            addCharacterToBuffer(c);
        } else {
            addToken(TokenType.NUMERIC);
            currentIndex--;
            state = State.START;

        }
    }

    //' in buffer
    private void symbolicConstantState(char c) {
        if (c == '\\') {//'\
            addCharacterToBuffer(c, State.BACKSLASH_IN_SYMBOLIC_CONSTANT);
        } else if (c == '\n') {
            addToken(TokenType.ERROR);
            addToken(TokenType.WHITE_SPACE, c);
            state = State.START;
        } else {
            addCharacterToBuffer(c, State.END_OF_SYMBOLIC_CONSTANT);
        }
    }


    //'\ in buffer
    private void backslashInSymbolicConstantState(char c) {
        if (c >= '0' && c <= '7') {
            addCharacterToBuffer(c, State.ONE_OCTAL_DIGIT_AFTER_BACKSLASH_IN_SYMBOLIC_CONSTANT);
        } else if (SymbolType.isEscapeSequence("\\" + c)) {
            addCharacterToBuffer(c, State.END_OF_SYMBOLIC_CONSTANT);
        } else {
            addToken(TokenType.ERROR);
            currentIndex--;
            state = State.START;
        }
    }

    private void oneOctalDigitAfterBackslashInSymbolicConstantState(char c) {
        if (c >= '0' && c <= '7') {
            addCharacterToBuffer(c, State.TWO_OCTAL_DIGIT_AFTER_BACKSLASH_IN_SYMBOLIC_CONSTANT);
        } else {
            currentIndex--;
            state = State.END_OF_SYMBOLIC_CONSTANT;
        }
    }

    private void twoOctalDigitAfterBackslashInSymbolicConstantState(char c) {
        if (c >= '0' && c <= '7') {
            addCharacterToBuffer(c, State.END_OF_SYMBOLIC_CONSTANT);
        } else {
            currentIndex--;
            state = State.END_OF_SYMBOLIC_CONSTANT;
        }
    }

    private void endOfSymbolicConstantState(char c) {
        if (c == '\'') {
            addCharacterToBuffer(c, State.START);
            addToken(TokenType.SYMBOLIC);
        } else if (c == '\n') {
            addToken(TokenType.ERROR);
            addToken(TokenType.WHITE_SPACE, c);
            state = State.START;
        } else {
            addCharacterToBuffer(c, State.ERROR_READ_SYMBOLIC_CONSTANT);
        }
    }

    private void errorReadSymbolicConstantState(char c) {
        if (c == '\n') {
            addToken(TokenType.ERROR);
            addToken(TokenType.WHITE_SPACE, c);
            state = State.START;
        } else if (c == '\'') {
            addCharacterToBuffer(c, State.START);
            addToken(TokenType.ERROR);
        } else if (c == '\\') {
            addCharacterToBuffer(c, State.BACKSLASH_INSIDE_ERROR_READ_SYMBOLIC_CONSTANT);
        } else {
            addCharacterToBuffer(c);
        }
    }

    private void backslashInsideErrorReadSymbolicConstantState(char c) {
        addCharacterToBuffer(c, State.ERROR_READ_SYMBOLIC_CONSTANT);
    }

    private void literalConstantState(char c) {
        if (c == '\\') {
            addCharacterToBuffer(c, State.BACKSLASH_IN_LITERAL_CONSTANT);
        } else if (c == '\"') {
            addCharacterToBuffer(c, State.START);
            addToken(TokenType.LITERAL);
        } else if (c == '\n') {
            addToken(TokenType.ERROR);
            addToken(TokenType.WHITE_SPACE, c);
            state = State.START;
        } else {
            addCharacterToBuffer(c);
        }
    }

    //"STRING\ in buffer
    private void backslashInLiteralConstantState(char c) {
        if (SymbolType.isEscapeSequence("\\" + c) || (c >= '0' && c <= '7')) {
            addCharacterToBuffer(c, State.LITERAL_CONSTANT);
        } else {
            addCharacterToBuffer(c, State.ERROR_READ_LITERAL_CONSTANT);
        }
    }

    private void errorReadLiteralConstantState(char c) {
        if (c == '\n') {
            addToken(TokenType.ERROR);
            addToken(TokenType.WHITE_SPACE, c);
            state = State.START;
        } else if (c == '\"') {
            addCharacterToBuffer(c, State.START);
            addToken(TokenType.ERROR);
        } else {
            addCharacterToBuffer(c);
        }
    }


}





