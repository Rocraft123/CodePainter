package com.rocraft.codepainter.style.engines;

import com.rocraft.codepainter.style.StyleEngine;
import com.rocraft.codepainter.token.Token;

public class DarkStyleEngine implements StyleEngine {

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
            color: #d4d4d4;
        }

        span {
            font-size: 14px;
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
            case TYPE -> "#4EC9B0";
            case SYMBOL -> "#D4D4D4";
            case ANNOTATION, FUNCTION -> "#DCDCAA";
            default -> "#9CDCFE";
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