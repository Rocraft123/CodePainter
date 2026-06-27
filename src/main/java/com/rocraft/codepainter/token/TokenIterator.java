package com.rocraft.codepainter.token;

import com.rocraft.codepainter.language.LanguageRules;
import com.rocraft.codepainter.utils.CharIterator;

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

        for (TokenDefinition token : languageRules.getLanguage().tokens()) {
            if (token.type().canStart(c, languageRules))
                return token.reader().read(iterator, new StringBuilder());
        }

        throw new IllegalStateException("Unknown token: " + c);
    }
}
