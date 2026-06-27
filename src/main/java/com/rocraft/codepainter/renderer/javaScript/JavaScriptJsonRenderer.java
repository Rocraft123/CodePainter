package com.rocraft.codepainter.renderer.javaScript;

import com.rocraft.codepainter.language.Languages;
import com.rocraft.codepainter.renderer.json.JsonFormatter;
import com.rocraft.codepainter.renderer.json.JsonRenderer;
import com.rocraft.codepainter.renderer.json.values.JsonArray;
import com.rocraft.codepainter.renderer.json.values.JsonObject;
import com.rocraft.codepainter.renderer.json.values.JsonString;
import com.rocraft.codepainter.token.Token;
import com.rocraft.codepainter.token.TokenIterator;
import com.rocraft.codepainter.token.TokenType;
import com.rocraft.codepainter.utils.CharIterator;

public class JavaJsonRenderer extends JsonRenderer {

    public JavaJsonRenderer() {
        super();
    }

    public JavaJsonRenderer(JsonFormatter formatter) {
        super(formatter);
    }

    @Override
    public String paint(String in) {
        CharIterator ci = new CharIterator(in);
        JsonArray object = new JsonArray();

        TokenIterator iterator = new TokenIterator(ci, Languages.JAVA_RULES);

        while (iterator.hasNext()) {
            Token token = iterator.next();

            if (isIgnoreWhiteSpace() && token.type() == TokenType.WHITESPACE) continue;
            object.add(toJson(token));
        }

        JsonFormatter formatter = getFormatter();
        if (formatter != null)
            return formatter.format(object);

        return object.toJson();
    }

    protected JsonObject toJson(Token token) {
        JsonObject object = new JsonObject();
        object.add("type", new JsonString(token.type().name()));
        object.add("context", new JsonString(token.context()));

        return object;
    }
}
