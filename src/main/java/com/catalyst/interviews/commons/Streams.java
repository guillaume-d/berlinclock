package com.catalyst.interviews.commons;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Helper functions for manipulating Java streams.
 */
public class Streams {

    public static <T> String join(String delimiter, T... things) {
        return Arrays.stream(things)
            .map(Object::toString)
            .collect(Collectors.joining(delimiter));
    }
}
