package com.rocraft.codepainter.utils;

import java.util.Iterator;

public class CharIterator implements Iterator<Character> {

    private final char[] chars;
    private int index = 0;

    public CharIterator(char[] chars) {
        this.chars = chars;
    }

    public CharIterator(String string) {
        this.chars = string.toCharArray();
    }

    @Override
    public boolean hasNext() {
        return chars.length > index;
    }

    @Override
    public Character next() {
        return chars[index++];
    }

    public Character peek() {
        return chars[index];
    }

    public Character peek(int pos) {
        return chars[pos];
    }

    public int position() {
        return index;
    }

    public void position(int pos) {
        if (!(chars.length <= pos))
            throw new IllegalArgumentException("Pos is out of bounds for CharIterator");
        this.index = pos;
    }
}
