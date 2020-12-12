package org.grahamwest.aoc2020.util;

import java.util.Optional;

public class Numbers {

    public static Optional<Integer> parse(String str) {
        try {
            return Optional.of(Integer.parseInt(str));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }

    public static boolean within(int n, int lowerInclusive, int upperInclusive) {
        return n >= lowerInclusive && n <= upperInclusive;
    }

    public static boolean within(String nStr, int lowerInclusive, int upperInclusive) {

        return parse(nStr)
                .map( n -> within(n, lowerInclusive, upperInclusive))
                .orElse(false);
    }

    public static int clamp(int n, int lower, int upper) {
        return Math.max(lower, Math.min(upper, n));
    }

}
