package com.rocraft.codepainter.renderer.html;

import com.rocraft.codepainter.renderer.Renderer;
import com.rocraft.codepainter.style.StyleEngine;

public abstract class HtmlRenderer implements Renderer {

    protected final StyleEngine styleEngine;
    protected final HtmlWriter htmlWriter;

    public HtmlRenderer(StyleEngine styleEngine) {
        this.styleEngine = styleEngine;
        this.htmlWriter = new HtmlWriter(styleEngine);
    }

    public HtmlRenderer(StyleEngine styleEngine, HtmlWriter htmlWriter) {
        this.styleEngine = styleEngine;
        this.htmlWriter = htmlWriter;
    }
}
