package com.rocraft.codepainter.token.nodes;

import com.rocraft.codepainter.token.NodeType;
import com.rocraft.codepainter.token.Token;

import java.util.List;

public class Node {

    private final NodeType type;
    private final Node parent;
    private final List<Node> children;
    private final List<Token> header;
    private final List<Token> tokens;

    private final int startLine;
    private final int endLine;

    public Node(NodeType type, Node parent, List<Node> children, List<Token> header, List<Token> tokens, int startLine, int endLine) {
        this.type = type;
        this.parent = parent;
        this.children = children;
        this.header = header;
        this.tokens = tokens;
        this.startLine = startLine;
        this.endLine = endLine;
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

    public List<Token> getTokens() {
        return tokens;
    }

    public int getStartLine() {
        return startLine;
    }

    public int getEndLine() {
        return endLine;
    }
}
