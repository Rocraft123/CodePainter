package com.rocraft.codepainter.renderer.javaScript;

import com.rocraft.codepainter.language.Languages;
import com.rocraft.codepainter.renderer.html.HtmlRenderer;
import com.rocraft.codepainter.renderer.html.HtmlWriter;
import com.rocraft.codepainter.renderer.renderers.SimpleHtmlRenderer;
import com.rocraft.codepainter.style.StyleEngine;
import com.rocraft.codepainter.style.engines.DarkStyleEngine;
import com.rocraft.codepainter.token.Token;
import com.rocraft.codepainter.token.TokenIterator;
import com.rocraft.codepainter.utils.CharIterator;

public class JavaScriptHtmlRenderer extends SimpleHtmlRenderer {

    public JavaScriptHtmlRenderer() {
        super(Languages.JAVA_SCRIPT_RULES);
    }

    public JavaScriptHtmlRenderer(StyleEngine styleEngine) {
        super(Languages.JAVA_SCRIPT_RULES, styleEngine);
    }

    public JavaScriptHtmlRenderer(StyleEngine styleEngine, HtmlWriter htmlWriter) {
        super(Languages.JAVA_SCRIPT_RULES, styleEngine, htmlWriter);
    }
}
