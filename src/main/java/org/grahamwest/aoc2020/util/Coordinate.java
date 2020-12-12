package org.grahamwest.aoc2020.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor
public class Coordinate {
    final int x;
    final int y;

    public static Coordinate from (int x, int y) {
        return new Coordinate(x, y);
    }

    public boolean equals(int x, int y) {
        return (x == this.x) && (y == this.y);
    }
}
