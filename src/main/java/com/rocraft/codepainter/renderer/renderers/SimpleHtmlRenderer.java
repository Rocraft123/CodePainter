package com.rocraft.codepainter.renderer.renderers;

import com.rocraft.codepainter.language.LanguageRules;
import com.rocraft.codepainter.renderer.html.HtmlRenderer;
import com.rocraft.codepainter.renderer.html.HtmlWriter;
import com.rocraft.codepainter.style.StyleEngine;
import com.rocraft.codepainter.style.engines.DarkStyleEngine;
import com.rocraft.codepainter.token.Token;
import com.rocraft.codepainter.token.TokenIterator;
import com.rocraft.codepainter.utils.CharIterator;

public class SimpleHtmlRenderer extends HtmlRenderer {

    private final LanguageRules rules;

    public SimpleHtmlRenderer(LanguageRules rules) {
        super(new DarkStyleEngine());
        this.rules = rules;
    }

    public SimpleHtmlRenderer(LanguageRules rules, StyleEngine styleEngine) {
        super(styleEngine);
        this.rules = rules;
    }

    public SimpleHtmlRenderer(LanguageRules rules, StyleEngine styleEngine, HtmlWriter htmlWriter) {
        super(styleEngine, htmlWriter);
        this.rules = rules;
    }

    @Override
    public String paint(String in) {
        CharIterator ci = new CharIterator(in);
        StringBuilder builder = new StringBuilder();

        TokenIterator iterator = new TokenIterator(ci, rules);

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
