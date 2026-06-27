package com.rocraft.codepainter.renderer.javaScript;

import com.rocraft.codepainter.language.Languages;
import com.rocraft.codepainter.renderer.html.HtmlRenderer;
import com.rocraft.codepainter.renderer.html.HtmlWriter;
import com.rocraft.codepainter.style.StyleEngine;
import com.rocraft.codepainter.style.engines.DarkStyleEngine;
import com.rocraft.codepainter.token.Token;
import com.rocraft.codepainter.token.TokenIterator;
import com.rocraft.codepainter.utils.CharIterator;

public class JavaHtmlRenderer extends HtmlRenderer {

    public JavaHtmlRenderer() {
        super(new DarkStyleEngine());
    }

    public JavaHtmlRenderer(StyleEngine styleEngine) {
        super(styleEngine);
    }

    public JavaHtmlRenderer(StyleEngine styleEngine, HtmlWriter htmlWriter) {
        super(styleEngine, htmlWriter);
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
