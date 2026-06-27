package com.rocraft.codepainter.style.engines;

import com.rocraft.codepainter.style.StyleEngine;
import com.rocraft.codepainter.token.Token;

public class HighContrastStyleEngine implements StyleEngine {

    @Override
    public String css() {
        return """
        body {
            background: #000000;
            margin: 0;
        }

        pre {
            font-family: Consolas, monospace;
            white-space: pre;
            color: #ffffff;
        }

        span {
            font-size: 14px;
        }
        """;
    }

    @Override
    public String tokenStyle(Token token) {
        return "span style=\"color: " + switch (token.type()) {
            case KEYWORD -> "#FF00FF";
            case STRING -> "#00FF00";
            case NUMBER -> "#FFFF00";
            case COMMENT -> "#888888";
            case TYPE -> "#00FFFF";
            case ANNOTATION, FUNCTION -> "#FF8800";
            default -> "#FFFFFF";
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