package com.rocraft.codepainter.token;

public record Token(TokenType type, String context) {

    public boolean isKeyword() {
        return type == TokenType.KEYWORD;
    }

    public boolean isSymbol(Character symbol) {
        return type == TokenType.SYMBOL && context.equals(symbol.toString());
    }
}
