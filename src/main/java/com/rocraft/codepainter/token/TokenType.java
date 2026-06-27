package com.rocraft.codepainter.token;

import com.rocraft.codepainter.language.LanguageRules;
import com.rocraft.codepainter.token.readers.*;

public enum TokenType {
    WHITESPACE,
    COMMENT,
    STRING,
    ANNOTATION, //Java Only
    FUNCTION,
    KEYWORD,
    NUMBER,
    NAME,
    TYPE,
    SYMBOL;

    public MatchResult match(String string, LanguageRules rules) {
        return rules.match(this, string);
    }

    public boolean canStart(Character aChar, LanguageRules rules) {
        return match(aChar.toString(), rules).possible();
    }
}
