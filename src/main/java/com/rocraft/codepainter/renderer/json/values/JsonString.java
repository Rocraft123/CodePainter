package com.rocraft.codepainter.renderer.json.values;

import com.rocraft.codepainter.renderer.json.JsonValue;

public class JsonString implements JsonValue {

    private final String value;

    public JsonString(String value) {
        this.value = value;
    }

    @Override
    public String toJson() {
        return "\"" + escape(value) + "\"";
    }

    private static String escape(String s) {
        StringBuilder out = new StringBuilder();

        for (char c : s.toCharArray()) {
            switch (c) {
                case '"'  -> out.append("\\\"");
                case '\\' -> out.append("\\\\");
                case '\n' -> out.append("\\n");
                case '\r' -> out.append("\\r");
                case '\t' -> out.append("\\t");
                case '\b' -> out.append("\\b");
                case '\f' -> out.append("\\f");
                default   -> out.append(c);
            }
        }

        return out.toString();
    }
}