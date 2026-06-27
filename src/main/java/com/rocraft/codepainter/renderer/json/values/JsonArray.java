package com.rocraft.codepainter.renderer.json.values;

import com.rocraft.codepainter.renderer.json.JsonValue;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class JsonArray implements JsonValue {

    private final List<JsonValue> values = new ArrayList<>();

    public JsonArray add(JsonValue value) {
        values.add(value);
        return this;
    }

    public List<JsonValue> getValues() {
        return values;
    }

    @Override
    public String toJson() {
        StringJoiner joiner = new StringJoiner(", ", "[", "]");

        for (JsonValue value : values)
            joiner.add(value.toJson());
        return joiner.toString();
    }
}
