package com.rocraft.codepainter.node.parsers.java;

import com.rocraft.codepainter.node.NodeParser;
import com.rocraft.codepainter.node.NodeType;
import com.rocraft.codepainter.node.nodes.Node;
import com.rocraft.codepainter.token.MatchResult;
import com.rocraft.codepainter.token.Token;
import com.rocraft.codepainter.token.TokenIterator;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class JPackageParser implements NodeParser {

    @Override
    public MatchResult matches(TokenIterator tokens) {
        Token peek = tokens.peek();
        return MatchResult.of(
                peek.isKeyword(),
                Objects.equals(peek.context(), "package")
        );
    }

    @Override
    public Node parse(TokenIterator tokens, Node parent) {
        List<Token> header = new ArrayList<>();

        while (tokens.hasNext()) {
            Token token = tokens.next();
            header.add(token);

            if (token.isSymbol(';'))
                break;
        }

        return new Node(NodeType.PACKAGE, parent, List.of(), header);
    }
}
