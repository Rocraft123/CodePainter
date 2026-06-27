package com.rocraft.codepainter.language;

import com.rocraft.codepainter.token.Token;
import com.rocraft.codepainter.token.TokenDefinition;
import com.rocraft.codepainter.utils.CharIterator;

import java.util.List;
import java.util.Set;

public interface Language {
    List<TokenDefinition> tokens();
    Set<String> keywords();
    Token classifyIdentifier(String identifier);

    default Token classifyIdentifier(String identifier, CharIterator iterator) {
        return null;
    }

    boolean isIdentifierStart(char c);
    boolean isIdentifierPart(char c);
}