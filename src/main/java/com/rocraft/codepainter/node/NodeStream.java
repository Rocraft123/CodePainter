package com.rocraft.codepainter.node;

import com.rocraft.codepainter.language.Language;
import com.rocraft.codepainter.node.nodes.Node;
import com.rocraft.codepainter.token.TokenIterator;

import java.util.Set;

public class NodeStream {

    private final Language language;
    private final TokenIterator iterator;

    public NodeStream(Language language, TokenIterator iterator) {
        this.language = language;
        this.iterator = iterator;
    }

    public Node next() {
        for (NodeDefinition definition : language.nodes()) {
            NodeParser parser = definition.parser();

            if (parser.matches(iterator).possible())
                return parser.parse(iterator, null);
        }

        return null;
    }
}
