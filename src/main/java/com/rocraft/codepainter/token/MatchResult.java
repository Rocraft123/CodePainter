package com.rocraft.codepainter.token;

public record MatchResult(boolean possible, boolean complete) {

    public static MatchResult of(boolean possible, boolean complete) {
        return new MatchResult(possible, complete);
    }

    public static MatchResult of(boolean possibleAndComplete) {
        return new MatchResult(possibleAndComplete, possibleAndComplete);
    }
}