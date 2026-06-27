package com.rocraft.codepainter.style.engines;

import com.rocraft.codepainter.style.StyleEngine;
import com.rocraft.codepainter.token.Token;

public class LightStyleEngine implements StyleEngine {

    @Override
    public String css() {
        return """
        body {
            background: #ffffff;
            margin: 0;
        }

        pre {
            font-family: Consolas, monospace;
            white-space: pre;
            color: #000000;
        }

        span {
            font-size: 14px;
        }
        """;
    }

    @Override
    public String tokenStyle(Token token) {
        return "span style=\"color: " + switch (token.type()) {
            case KEYWORD -> "#0000FF";
            case STRING -> "#008000";
            case NUMBER -> "#098658";
            case COMMENT -> "#808080";
            case TYPE -> "#267F99";
            case ANNOTATION, FUNCTION -> "#795E26";
            case SYMBOL -> "#000000";
            default -> "#001080";
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