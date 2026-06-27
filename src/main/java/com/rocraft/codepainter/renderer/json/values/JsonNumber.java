package com.rocraft.codepainter.renderer.json.values;

import com.rocraft.codepainter.renderer.json.JsonValue;

public class JsonNumber implements JsonValue {

    private final Number value;

    public JsonNumber(Number value) {
        this.value = value;
    }

    @Override
    public String toJson() {
        return value.toString();
    }
}