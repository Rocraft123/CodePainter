package com.rocraft.codepainter.renderer.java;

import com.rocraft.codepainter.language.Languages;
import com.rocraft.codepainter.renderer.json.JsonFormatter;
import com.rocraft.codepainter.renderer.renderers.SimpleJsonRenderer;

public class JavaJsonRenderer extends SimpleJsonRenderer {

    public JavaJsonRenderer() {
        super(Languages.JAVA_RULES);
    }

    public JavaJsonRenderer(JsonFormatter formatter) {
        super(Languages.JAVA_RULES, formatter);
    }
}
