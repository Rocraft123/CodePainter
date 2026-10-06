package com.rocraft.codepainter.renderer.java;

import com.rocraft.codepainter.language.Languages;
import com.rocraft.codepainter.renderer.html.HtmlWriter;
import com.rocraft.codepainter.renderer.renderers.SimpleHtmlRenderer;
import com.rocraft.codepainter.style.StyleEngine;
import com.rocraft.codepainter.style.engines.DarkStyleEngine;

public class JavaHtmlRenderer extends SimpleHtmlRenderer {

    public JavaHtmlRenderer() {
        super(Languages.JAVA_RULES, new DarkStyleEngine());
    }

    public JavaHtmlRenderer(StyleEngine styleEngine) {
        super(Languages.JAVA_RULES, styleEngine);
    }

    public JavaHtmlRenderer(StyleEngine styleEngine, HtmlWriter htmlWriter) {
        super(Languages.JAVA_RULES, styleEngine, htmlWriter);
    }
}
