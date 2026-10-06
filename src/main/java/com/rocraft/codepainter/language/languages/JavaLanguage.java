package com.rocraft.codepainter.language.languages;

import com.rocraft.codepainter.language.Language;
import com.rocraft.codepainter.node.NodeDefinition;
import com.rocraft.codepainter.node.NodeType;
import com.rocraft.codepainter.node.parsers.java.JPackageParser;
import com.rocraft.codepainter.token.Token;
import com.rocraft.codepainter.token.TokenDefinition;
import com.rocraft.codepainter.token.TokenType;
import com.rocraft.codepainter.token.readers.*;
import com.rocraft.codepainter.utils.CharIterator;

import java.util.List;
import java.util.Set;

public class JavaLanguage implements Language {

    private static final Set<String> KEYWORDS = Set.of(
            "abstract", "continue", "for", "new", "switch", "assert", "default",
            "goto", "package", "synchronized", "boolean", "do", "if", "private",
            "this", "break", "double", "implements", "protected", "throw",
            "byte", "else", "import", "public", "throws", "case", "enum",
            "instanceof", "return", "transient", "catch", "extends", "int",
            "short", "try", "char", "final", "interface", "static", "void",
            "class", "finally", "long", "strictfp", "volatile", "const","float",
            "native", "super", "while", "null", "true", "false"
    );

    private final NameReader nameReader = new NameReader(this);

    @Override
    public List<NodeDefinition> nodes() {
        return List.of(
                NodeDefinition.of(NodeType.PACKAGE, new JPackageParser()),
                NodeDefinition.of(NodeType.IMPORT, null),
                NodeDefinition.of(NodeType.CLASS, null),
                NodeDefinition.of(NodeType.INTERFACE, null),
                NodeDefinition.of(NodeType.ENUM, null),
                NodeDefinition.of(NodeType.RECORD, null),
                NodeDefinition.of(NodeType.METHOD, null),
                NodeDefinition.of(NodeType.CONSTRUCTOR, null),
                NodeDefinition.of(NodeType.FIELD, null),
                NodeDefinition.of(NodeType.LOCAL_VARIABLE, null),
                NodeDefinition.of(NodeType.IF, null),
                NodeDefinition.of(NodeType.ELSE, null),
                NodeDefinition.of(NodeType.SWITCH, null),
                NodeDefinition.of(NodeType.CASE, null),
                NodeDefinition.of(NodeType.FOR, null),
                NodeDefinition.of(NodeType.WHILE, null),
                NodeDefinition.of(NodeType.DO_WHILE, null),
                NodeDefinition.of(NodeType.TRY, null),
                NodeDefinition.of(NodeType.CASE, null),
                NodeDefinition.of(NodeType.COMMENT, null),
                NodeDefinition.of(NodeType.BLOCK, null)
        );
    }

    @Override
    public List<TokenDefinition> tokens() {
        return List.of(TokenDefinition.of(TokenType.WHITESPACE, new WhiteSpaceReader()),
                TokenDefinition.of(TokenType.COMMENT, new CommentReader()),
                TokenDefinition.of(TokenType.STRING, new StringReader()),
                TokenDefinition.of(TokenType.ANNOTATION, new AnnotationReader()),
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
    public Token classifyIdentifier(String text) {
        if (keywords().contains(text))
            return new Token(TokenType.KEYWORD, text);

        if (Character.isUpperCase(text.charAt(0)))
            return new Token(TokenType.TYPE, text);
        return new Token(TokenType.NAME, text);
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
        return Character.isJavaIdentifierStart(c);
    }

    @Override
    public boolean isIdentifierPart(char c) {
        return Character.isJavaIdentifierPart(c);
    }
}