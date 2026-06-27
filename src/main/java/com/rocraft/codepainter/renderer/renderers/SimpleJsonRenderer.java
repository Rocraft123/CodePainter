package com.rocraft.codepainter.renderer.renderers;

import com.rocraft.codepainter.language.LanguageRules;
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

public class SimpleJsonRenderer extends JsonRenderer {

    private final LanguageRules rules;

    public SimpleJsonRenderer(LanguageRules rules) {
        super();
        this.rules = rules;
    }

    public SimpleJsonRenderer(LanguageRules rules, JsonFormatter formatter) {
        super(formatter);
        this.rules = rules;
    }

    @Override
    public String paint(String in) {
        CharIterator ci = new CharIterator(in);
        JsonArray object = new JsonArray();

        TokenIterator iterator = new TokenIterator(ci, rules);

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
