package com.rocraft.codepainter.utils;

import com.rocraft.codepainter.language.LanguageRules;
import com.rocraft.codepainter.token.Token;
import com.rocraft.codepainter.token.TokenType;

import java.util.Iterator;

public class TokenIterator implements Iterator<Token> {

    private final CharIterator iterator;
    private final LanguageRules languageRules;

    public TokenIterator(CharIterator iterator, LanguageRules languageRules) {
        this.iterator = iterator;
        this.languageRules = languageRules;
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public Token next() {
        char c = iterator.peek();

        for (TokenType type : TokenType.values()) {
            if (type.canStart(c, languageRules))
                return type.read(iterator, new StringBuilder());
        }

        throw new IllegalStateException("Unknown token: " + c);
    }
}
