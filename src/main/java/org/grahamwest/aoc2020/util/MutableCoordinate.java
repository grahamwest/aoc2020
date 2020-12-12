package org.grahamwest.aoc2020.util;

public class MutableCoordinate extends Coordinate {

    public MutableCoordinate(int x, int y) {
        super(x,y);
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void set(int x, int y) {
        this.setX(x);
        this.setY(y);
    }

    public static MutableCoordinate from(Coordinate c) {
        return new MutableCoordinate(c.getX(), c.getY());
    }

    public Coordinate immutable() {
        return Coordinate.from(this.x, this.y);
    }

}
