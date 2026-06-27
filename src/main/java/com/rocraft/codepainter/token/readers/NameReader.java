package com.rocraft.codepainter.token.readers;

import com.rocraft.codepainter.language.Language;
import com.rocraft.codepainter.language.languages.JavaScriptLanguage;
import com.rocraft.codepainter.token.Token;
import com.rocraft.codepainter.token.TokenReader;
import com.rocraft.codepainter.utils.CharIterator;

public class NameReader implements TokenReader {

    private final Language language;

    public NameReader(Language language) {
        this.language = language;
    }

    @Override
    public Token read(CharIterator iterator, StringBuilder builder) {
        builder.setLength(0);

        do builder.append(iterator.next());
        while (iterator.hasNext()
                && language.isIdentifierPart(iterator.peek()));

        Token token = language.classifyIdentifier(builder.toString(), iterator);
        return token != null ? token : language.classifyIdentifier(builder.toString());
    }
}