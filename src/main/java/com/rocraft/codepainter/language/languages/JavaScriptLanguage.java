package com.rocraft.codepainter.language;

import java.util.Set;

public class JavaScriptLanguage implements Language {

    private static final Set<String> KEYWORDS = Set.of(
            "break", "case", "catch", "class", "const", "continue", "debugger",
            "default", "delete", "do", "else", "export", "extends", "finally",
            "for", "function", "if", "import", "in", "instanceof", "new",
            "return", "super", "switch", "this", "throw", "try", "typeof",
            "var", "void", "while", "with", "enum", "let", "static", "yield",
            "implements", "interface", "package", "private", "protected",
            "public", "async", "await", "as", "from", "get", "set", "of",
            "null", "true", "false"
    );

    @Override
    public Set<String> keywords() {
        return KEYWORDS;
    }
}