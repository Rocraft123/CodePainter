package com.rocraft.codepainter.token;

import com.rocraft.codepainter.utils.CharIterator;

public interface TokenReader {
    Token read(CharIterator iterator, StringBuilder builder);
}
