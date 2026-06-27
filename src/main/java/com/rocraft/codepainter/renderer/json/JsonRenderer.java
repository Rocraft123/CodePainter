package com.rocraft.codepainter.renderer.json;

import com.rocraft.codepainter.renderer.Renderer;

public abstract class JsonRenderer implements Renderer {

    private boolean ignoreWhiteSpace = true;
    private final JsonFormatter formatter;

    protected JsonRenderer() {
        this.formatter = null;
    }

    protected JsonRenderer(JsonFormatter formatter) {
        this.formatter = formatter;
    }

    public boolean isIgnoreWhiteSpace() {
        return ignoreWhiteSpace;
    }

    public void setIgnoreWhiteSpace(boolean ignoreWhiteSpace) {
        this.ignoreWhiteSpace = ignoreWhiteSpace;
    }

    public JsonFormatter getFormatter() {
        return formatter;
    }
}
