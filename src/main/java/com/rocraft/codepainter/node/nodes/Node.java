package com.rocraft.codepainter.node.nodes;

import com.rocraft.codepainter.node.NodeType;
import com.rocraft.codepainter.token.Token;

import java.util.List;

public class Node {

    private final NodeType type;
    private final Node parent;
    private final List<Node> children;
    private final List<Token> header;

    public Node(NodeType type, Node parent, List<Node> children, List<Token> header) {
        this.type = type;
        this.parent = parent;
        this.children = children;
        this.header = header;
    }

    public NodeType getType() {
        return type;
    }

    public Node getParent() {
        return parent;
    }

    public List<Node> getChildren() {
        return children;
    }

    public List<Token> getHeader() {
        return header;
    }
}
