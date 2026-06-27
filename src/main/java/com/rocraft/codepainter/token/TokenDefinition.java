package com.rocraft.codepainter.token;

public record TokenDefinition(TokenType type, TokenReader reader) {
    public static TokenDefinition of(TokenType type, TokenReader reader) {
        return new TokenDefinition(type, reader);
    }
}