package com.rocraft.codepainter.node;

public record NodeDefinition(NodeType type, NodeParser parser) {

    public static NodeDefinition of(NodeType type, NodeParser parser) {
        return new NodeDefinition(type, parser);
    }
}
