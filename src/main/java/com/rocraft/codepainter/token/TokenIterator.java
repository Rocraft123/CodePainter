package com.rocraft.codepainter.token;

import com.rocraft.codepainter.language.LanguageRules;
import com.rocraft.codepainter.utils.CharIterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TokenIterator implements Iterator<Token> {

    private final List<Token> tokens = new ArrayList<>();
    private int index = 0;

    public TokenIterator(CharIterator iterator, LanguageRules languageRules) {
        //noinspection LoopStatementThatDoesntLoop - It does loop, in the reader it uses .next() alot;
        while (iterator.hasNext()) {
            char c = iterator.peek();

            for (TokenDefinition definition : languageRules.getLanguage().tokens()) {
                if (definition.type().canStart(c, languageRules)) {
                    Token token = definition.reader().read(iterator, new StringBuilder());
                    tokens.add(token);
                }
            }

            throw new IllegalStateException("Unknown token: " + c);
        }
    }

    @Override
    public boolean hasNext() {
        return tokens.size() > index;
    }

    @Override
    public Token next() {
        return tokens.get(index++);
    }

    public Token peek() {
        return tokens.get(index);
    }

    public Token peek(int pos) {
        return tokens.get(pos);
    }

    public int position() {
        return index;
    }

    public void position(int pos) {
        this.index = pos;
    }
}
