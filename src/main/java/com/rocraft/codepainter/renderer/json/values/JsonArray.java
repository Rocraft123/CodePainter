package com.rocraft.codepainter.renderer.json;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class JsonArray implements JsonValue {

    private final List<JsonValue> values = new ArrayList<>();

    public JsonArray add(JsonValue value) {
        values.add(value);
        return this;
    }

    @Override
    public String toJson() {
        StringJoiner joiner = new StringJoiner(", ", "[", "]");

        for (JsonValue value : values) {
            joiner.add(value.toJson());
        }

        return joiner.toString();
    }
}
