package com.rocraft.codepainter.renderer.json;

import com.rocraft.codepainter.renderer.json.values.JsonArray;
import com.rocraft.codepainter.renderer.json.values.JsonObject;

import java.util.List;
import java.util.Map;

public class JsonFormatter {

    private final String intent;

    public JsonFormatter() {
        this.intent = "  ";
    }

    public JsonFormatter(String intent) {
        this.intent = intent;
    }

    public JsonFormatter(int intent) {
        this.intent = " ".repeat(intent);
    }

    public String format(JsonValue value) {
        StringBuilder sb = new StringBuilder();
        format(value, sb, 0);
        return sb.toString();
    }

    private void format(JsonValue value, StringBuilder sb, int depth) {
        if (value instanceof JsonObject object) formatObject(object, sb, depth);
        else if (value instanceof JsonArray array) formatArray(array, sb, depth);
        else sb.append(value.toJson());
    }

    private void formatObject(JsonObject object, StringBuilder sb, int depth) {
        Map<String, JsonValue> values = object.getValues();
        if (values.isEmpty()) {
            sb.append("{}");
            return;
        }

        sb.append("{\n");
        int i = 0;
        int size = values.size();
        for (var entry : values.entrySet()) {
            indent(sb, depth + 1);
            sb.append('"').append(entry.getKey()).append("\": ");
            format(entry.getValue(), sb, depth + 1);
            if (++i < size) sb.append(',');
            sb.append('\n');
        }
        indent(sb, depth);
        sb.append('}');
    }

    private void formatArray(JsonArray array, StringBuilder sb, int depth) {
        List<JsonValue> values = array.getValues();
        if (values.isEmpty()) {
            sb.append("[]");
            return;
        }

        sb.append("[\n");
        for (int i = 0; i < values.size(); i++) {
            indent(sb, depth + 1);
            format(values.get(i), sb, depth + 1);
            if (i < values.size() - 1) sb.append(',');
            sb.append('\n');
        }
        indent(sb, depth);
        sb.append(']');
    }

    private void indent(StringBuilder sb, int depth) {
        sb.append(intent.repeat(depth));
    }
}