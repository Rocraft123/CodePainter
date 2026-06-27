package com.rocraft.codepainter.token.readers;

import com.rocraft.codepainter.token.Token;
import com.rocraft.codepainter.token.TokenReader;
import com.rocraft.codepainter.token.TokenType;
import com.rocraft.codepainter.utils.CharIterator;

public class StringReader implements TokenReader {

    @Override
    public Token read(CharIterator iterator, StringBuilder builder) {
        builder.setLength(0);

        char quote = iterator.next();
        builder.append(quote);

        boolean escaped = false;

        while (iterator.hasNext()) {
            char c = iterator.next();
            builder.append(c);

            if (escaped) {
                escaped = false;
                continue;
            }

            if (c == '\\') {
                escaped = true;
                continue;
            }

            if (c == quote)
                break;
        }

        return new Token(TokenType.STRING, builder.toString());
    }
}