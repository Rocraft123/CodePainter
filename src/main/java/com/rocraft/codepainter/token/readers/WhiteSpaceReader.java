package com.rocraft.codepainter.token.readers;

import com.rocraft.codepainter.token.Token;
import com.rocraft.codepainter.token.TokenReader;
import com.rocraft.codepainter.token.TokenType;
import com.rocraft.codepainter.utils.CharIterator;

public class WhiteSpaceReader implements TokenReader {

    @Override
    public Token read(CharIterator iterator, StringBuilder builder) {
        while (iterator.hasNext() && Character.isWhitespace(iterator.peek()))
            builder.append(iterator.next());
        return new Token(TokenType.WHITESPACE, builder.toString());
    }
}
