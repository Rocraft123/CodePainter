package com.rocraft.codepainter.token.readers;

import com.rocraft.codepainter.token.Token;
import com.rocraft.codepainter.token.TokenReader;
import com.rocraft.codepainter.token.TokenType;
import com.rocraft.codepainter.utils.CharIterator;

public class SymbolReader implements TokenReader {

    @Override
    public Token read(CharIterator iterator, StringBuilder builder) {
        builder.setLength(0);
        builder.append(iterator.next());
        return new Token(TokenType.SYMBOL, builder.toString());
    }
}