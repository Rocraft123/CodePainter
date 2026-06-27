package com.rocraft.codepainter.language;

import com.rocraft.codepainter.token.MatchResult;
import com.rocraft.codepainter.token.TokenType;

public interface LanguageRules {
    Language getLanguage();
    MatchResult match(TokenType type, String input);
}
