package com.rocraft.codepainter.style.engines;

import com.rocraft.codepainter.color.TokenStyle;
import com.rocraft.codepainter.style.StyleEngine;
import com.rocraft.codepainter.token.Token;

public class DefaultStyleEngine implements StyleEngine {

    @Override
    public String css() {
        return """
        body {
            background: #1e1e1e;
            margin: 0;
        }

        pre {
            font-family: Consolas, monospace;
            white-space: pre;
        }
        """;
    }

    @Override
    public String tokenStyle(Token token) {
        return "style=\"color: " + switch (token.type()) {
            case KEYWORD -> "#C586C0";
            case STRING -> "#CE9178";
            case NUMBER -> "#B5CEA8";
            case COMMENT -> "#6A9955";
            case ANNOTATION -> "#DCDCAA";
            case TYPE -> "#4EC9B0";
            case NAME -> "#9CDCFE";
            default -> "#D4D4D4";
        } + "\"";
    }

    @Override
    public String wrapStart() {
        return "<pre>";
    }

    @Override
    public String wrapEnd() {
        return "</pre>";
    }
}