package com.rocraft.codepainter.language.rules;

import com.rocraft.codepainter.language.Language;
import com.rocraft.codepainter.language.LanguageRules;
import com.rocraft.codepainter.language.languages.JavaScriptLanguage;
import com.rocraft.codepainter.token.MatchResult;
import com.rocraft.codepainter.token.TokenType;

public class JavaScriptRules implements LanguageRules {

    private final JavaScriptLanguage language;

    public JavaScriptRules(JavaScriptLanguage language) {
        this.language = language;
    }

    @Override
    public Language getLanguage() {
        return language;
    }

    @Override
    public MatchResult match(TokenType type, String input) {
        return switch (type) {
            case WHITESPACE -> MatchResult.of(input.isBlank());
            case COMMENT -> MatchResult.of(input.startsWith("/"));
            case STRING -> MatchResult.of(input.startsWith("\"") ||
                    input.startsWith("'") || input.startsWith("`"));
            case NUMBER -> MatchResult.of(isNumeric(input));
            case NAME -> MatchResult.of(input.isEmpty() || language.isIdentifierStart(input.charAt(0)));
            case SYMBOL ->
                    MatchResult.of(true, false);
            default -> MatchResult.of(false, false);
        };
    }

    private boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }
}