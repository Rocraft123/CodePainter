package com.rocraft.codepainter.renderer.html;

import com.rocraft.codepainter.style.StyleEngine;

public class HtmlWriter {

    private final StyleEngine style;

    public HtmlWriter(StyleEngine style) {
        this.style = style;
    }

    public String write(String codeHtml) {
        return "<html><head><style>" +
                style.css() +
                "</style></head><body>" +
                style.wrapStart() +
                codeHtml +
                style.wrapEnd() +
                "</body></html>";
    }

    public String escape(String text) {
        return text
                .replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;");
    }
}