package com.rocraft.codepainter.renderer.json.values;

import com.rocraft.codepainter.renderer.json.JsonValue;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.StringJoiner;

public class JsonObject implements JsonValue {

    private final Map<String, JsonValue> values = new LinkedHashMap<>();

    public JsonObject add(String key, JsonValue value) {
        values.put(key, value);
        return this;
    }

    public Map<String, JsonValue> getValues() {
        return values;
    }

    @Override
    public String toJson() {
        StringJoiner joiner = new StringJoiner(", ", "{", "}");

        for (var entry : values.entrySet()) {
            joiner.add("\"" + entry.getKey() + "\": " + entry.getValue().toJson());
        }

        return joiner.toString();
    }
}