package com.rocraft.codepainter.language.rules;

import com.rocraft.codepainter.language.Language;
import com.rocraft.codepainter.language.languages.JavaLanguage;
import com.rocraft.codepainter.language.LanguageRules;
import com.rocraft.codepainter.token.MatchResult;
import com.rocraft.codepainter.token.TokenType;

public class JavaRules implements LanguageRules {

    private final JavaLanguage language;

    public JavaRules(JavaLanguage language) {
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
            case STRING -> MatchResult.of(input.startsWith("\"") || input.startsWith("'"));
            case ANNOTATION -> MatchResult.of(input.startsWith("@"));
            case KEYWORD -> MatchResult.of(
                    language.keywords().stream().anyMatch(k -> k.startsWith(input)),
                    language.keywords().contains(input));
            case NUMBER -> MatchResult.of(isNumeric(input));
            case NAME -> {
                if (!Character.isJavaIdentifierStart(input.charAt(0)))
                    yield MatchResult.of(false, false);

                for (int i = 1; i < input.length(); i++) {
                    if (!Character.isJavaIdentifierPart(input.charAt(i)))
                        yield MatchResult.of(false, false);
                }

                yield MatchResult.of(true, true);
            }
            case TYPE -> MatchResult.of(Character.isUpperCase(input.charAt(0)));
            case SYMBOL -> MatchResult.of(true, false);
            default -> MatchResult.of(false, false);
        };
    }

    private boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }
}