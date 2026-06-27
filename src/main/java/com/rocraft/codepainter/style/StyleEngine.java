package com.rocraft.codepainter.style;

import com.rocraft.codepainter.token.Token;

public interface StyleEngine {
    String css();
    String tokenStyle(Token token);
    String wrapStart();
    String wrapEnd();
}
