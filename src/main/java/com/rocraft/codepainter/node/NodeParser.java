package com.rocraft.codepainter.node;

import com.rocraft.codepainter.node.nodes.Node;
import com.rocraft.codepainter.token.MatchResult;
import com.rocraft.codepainter.token.TokenIterator;

public interface NodeParser {
    MatchResult matches(TokenIterator iterator);
    Node parse(TokenIterator iterator, Node parent);
}
