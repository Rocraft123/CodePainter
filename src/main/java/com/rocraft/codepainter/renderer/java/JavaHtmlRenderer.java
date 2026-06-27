package com.rocraft.codepainter.renderer.java;

import com.rocraft.codepainter.language.Languages;
import com.rocraft.codepainter.renderer.html.HtmlWriter;
import com.rocraft.codepainter.renderer.renderers.SimpleHtmlRenderer;
import com.rocraft.codepainter.style.StyleEngine;
import com.rocraft.codepainter.style.engines.DarkStyleEngine;
import com.rocraft.codepainter.token.Token;
import com.rocraft.codepainter.utils.CharIterator;
import com.rocraft.codepainter.token.TokenIterator;

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

    @Override
    public String paint(String in) {
        CharIterator ci = new CharIterator(in);
        StringBuilder builder = new StringBuilder();

        TokenIterator iterator = new TokenIterator(ci, Languages.JAVA_RULES);

        while (iterator.hasNext()) {
            Token token = iterator.next();

            builder.append("<span ");
            builder.append(styleEngine.tokenStyle(token))
                    .append(">");
            builder.append(htmlWriter.escape(token.context()));
            builder.append("</span>");
        }

        return htmlWriter.write(builder.toString());
    }

}
