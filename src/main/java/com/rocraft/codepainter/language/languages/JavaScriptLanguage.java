package com.rocraft.codepainter.language.languages;

import com.rocraft.codepainter.language.Language;
import com.rocraft.codepainter.node.NodeDefinition;
import com.rocraft.codepainter.token.Token;
import com.rocraft.codepainter.token.TokenDefinition;
import com.rocraft.codepainter.token.TokenType;
import com.rocraft.codepainter.token.readers.*;
import com.rocraft.codepainter.utils.CharIterator;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class JavaScriptLanguage implements Language {

    private final NameReader nameReader = new NameReader(this);

    private static final Set<String> KEYWORDS = Set.of(
            "break", "case", "catch", "class", "const", "continue", "debugger",
            "default", "delete", "do", "else", "export", "extends", "finally",
            "for", "function", "if", "import", "in", "instanceof", "new",
            "return", "super", "switch", "this", "throw", "try", "typeof",
            "var", "void", "while", "with", "enum", "let", "static", "yield",
            "implements", "interface", "package", "private", "protected",
            "public", "async", "await", "as", "from", "get", "set", "of",
            "null", "true", "false"
    );

    @Override
    public List<NodeDefinition> nodes() {
        return List.of();
    }

    @Override
    public List<TokenDefinition> tokens() {
        return List.of(TokenDefinition.of(TokenType.WHITESPACE, new WhiteSpaceReader()),
                TokenDefinition.of(TokenType.COMMENT, new CommentReader()),
                TokenDefinition.of(TokenType.STRING, new StringReader()),
                TokenDefinition.of(TokenType.NUMBER, new NumberReader()),
                TokenDefinition.of(TokenType.KEYWORD, nameReader),
                TokenDefinition.of(TokenType.FUNCTION, nameReader),
                TokenDefinition.of(TokenType.NAME, nameReader),
                TokenDefinition.of(TokenType.TYPE, nameReader),
                TokenDefinition.of(TokenType.SYMBOL, new SymbolReader())
        );
    }

    @Override
    public Set<String> keywords() {
        return KEYWORDS;
    }

    @Override
    public Token classifyIdentifier(String identifier) {
        if (keywords().contains(identifier))
            return new Token(TokenType.KEYWORD, identifier);

        if (Character.isUpperCase(identifier.charAt(0)))
            return new Token(TokenType.TYPE, identifier);
        return new Token(TokenType.NAME, identifier);
    }

    @Override
    public Token classifyIdentifier(String identifier, CharIterator iterator) {
        if (keywords().contains(identifier))
            return new Token(TokenType.KEYWORD, identifier);

        if (Character.isUpperCase(identifier.charAt(0)))
            return new Token(TokenType.TYPE, identifier);
        if (iterator.peek() == '(')
            return new Token(TokenType.FUNCTION, identifier);
        return new Token(TokenType.NAME, identifier);
    }

    @Override
    public boolean isIdentifierStart(char c) {
        return Character.isLetter(c) || c == '_' || c == '$';
    }

    @Override
    public boolean isIdentifierPart(char c) {
        return Character.isLetterOrDigit(c) || c == '_' || c == '$';
    }
}