package com.rocraft.codepainter.token.readers;

import com.rocraft.codepainter.token.Token;
import com.rocraft.codepainter.token.TokenReader;
import com.rocraft.codepainter.token.TokenType;
import com.rocraft.codepainter.utils.CharIterator;

public class AnnotationReader implements TokenReader {

    @Override
    public Token read(CharIterator iterator, StringBuilder builder) {
        builder.setLength(0);

        do builder.append(iterator.next());
        while (iterator.hasNext() &&
                Character.isJavaIdentifierPart(iterator.peek()));

        return new Token(TokenType.ANNOTATION, builder.toString());
    }
}