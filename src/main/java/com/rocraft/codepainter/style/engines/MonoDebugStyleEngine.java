package com.rocraft.codepainter.style.engines;

import com.rocraft.codepainter.style.StyleEngine;
import com.rocraft.codepainter.token.Token;

public class MonoDebugStyleEngine implements StyleEngine {

    @Override
    public String css() {
        return """
        body {
            background: #111;
            margin: 0;
        }

        pre {
            font-family: monospace;
            white-space: pre;
            color: #ccc;
        }
        """;
    }

    @Override
    public String tokenStyle(Token token) {
        return "span style=\"color: #cccccc\"";
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