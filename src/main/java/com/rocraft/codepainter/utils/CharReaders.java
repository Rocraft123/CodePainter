package com.rocraft.codepainter.utils;

import java.util.function.Predicate;

public class CharReaders {

    public static String readUntilWhitespace(CharIterator iterator) {
        return readUntil(iterator, it -> Character.isWhitespace(it.peek()));
    }

    public static String readWhile(CharIterator iterator, Predicate<CharIterator> stopCondition) {
        return readUntil(iterator, it -> !stopCondition.test(it));
    }

    public static String readUntil(CharIterator iterator, Predicate<CharIterator> stopCondition) {
        StringBuilder builder = new StringBuilder();

        while (iterator.hasNext()) {
            if (stopCondition.test(iterator))
                break;

            builder.append(iterator.next());
        }

        return builder.toString();
    }

    public static String readLine(CharIterator iterator) {
        StringBuilder builder = new StringBuilder();

        while (iterator.hasNext()) {
            char c = iterator.next();
            if (c == '\n') break;

            if (c == '\r') {
                if (iterator.hasNext() && iterator.peek() == '\n')
                    iterator.next();
                break;
            }

            builder.append(c);
        }

        return builder.toString();
    }

    public static String readUntil(CharIterator iterator, String... strings) {
        StringBuilder builder = new StringBuilder();

        for (CharIterator it = iterator; it.hasNext();) {
            char next = it.next();
            builder.append(next);

            String build = builder.toString();

            for (String string : strings) {
                if (build.endsWith(string))
                    return build;
            }
        }

        return builder.toString();
    }
}
