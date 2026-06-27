package com.rocraft.codepainter.token.readers;

import com.rocraft.codepainter.token.Token;
import com.rocraft.codepainter.token.TokenReader;
import com.rocraft.codepainter.token.TokenType;
import com.rocraft.codepainter.utils.CharIterator;

public class NumberReader implements TokenReader {

    @Override
    public Token read(CharIterator iterator, StringBuilder builder) {
        builder.setLength(0);

        boolean dot = false;

        while (iterator.hasNext()) {
            char c = iterator.peek();

            if (Character.isDigit(c) || c == '_' ||
                    c == 'L'  || c == 'F' ||  c == 'D')
                builder.append(iterator.next());
            else if (c == '.'  && !dot) {
                dot = true;
                builder.append(iterator.next());
            } else break;

        }

        return new Token(TokenType.NUMBER, builder.toString());
    }
}