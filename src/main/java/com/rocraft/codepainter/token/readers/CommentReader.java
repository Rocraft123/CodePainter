package com.rocraft.codepainter.token.readers;

import com.rocraft.codepainter.token.Token;
import com.rocraft.codepainter.token.TokenReader;
import com.rocraft.codepainter.token.TokenType;
import com.rocraft.codepainter.utils.CharIterator;

public class CommentReader implements TokenReader {

    @Override
    public Token read(CharIterator iterator, StringBuilder builder) {
        builder.setLength(0);

        builder.append(iterator.next());
        builder.append(iterator.next());

        if (builder.toString().equals("//")) {
            while (iterator.hasNext()) {
                char c = iterator.next();
                builder.append(c);

                if (c == '\n')
                    break;
            }
        } else if (builder.toString().equals("/*")) {
            while (iterator.hasNext()) {
                char c = iterator.next();
                builder.append(c);

                if (c == '*' && iterator.hasNext() && iterator.peek() == '/') {
                    builder.append(iterator.next());
                    break;
                }
            }
        }

        return new Token(TokenType.COMMENT, builder.toString());
    }
}