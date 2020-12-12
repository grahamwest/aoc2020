package org.grahamwest.aoc2020.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import static org.grahamwest.aoc2020.util.Numbers.clamp;

@Getter
@AllArgsConstructor
@ToString
public class Coordinate {
    protected int x;
    protected int y;

    public static Coordinate from (int x, int y) {
        return new Coordinate(x, y);
    }

    public int manhattan() {
        return Math.abs(this.getX()) + Math.abs(this.getY());
    }

    public boolean equals(int x, int y) {
        return (x == this.x) && (y == this.y);
    }

    public Coordinate multiply(int magnitude) {
        return Coordinate.from(this.getX() * magnitude, this.getY() * magnitude);
    }

    public Coordinate multiply(Coordinate c) {
        return Coordinate.from( this.getX() * c.getX(), this.getY() * c.getY());
    }

    public Coordinate add(int magnitude) {
        return Coordinate.from(this.getX() + magnitude, this.getY() + magnitude);
    }

    public Coordinate add(Coordinate c) {
        return Coordinate.from(this.getX() + c.getX(), this.getY() + c.getY());
    }

    public Coordinate directionVector() {
        return Coordinate.from( clamp(x, -1, 1), clamp(y, -1, 1) );
    }

    public Coordinate rotate90() {
        return Coordinate.from( -1 * y, x );
    }
}
