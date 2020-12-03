package org.grahamwest.aoc2020.util;

public class DynamicGrid {

    final String[] source;
    final int width;
    final int height;

    private DynamicGrid(String[] source, int width, int height) {
        this.source = source;
        this.width = width;
        this.height = height;
    }

    public int getHeight() {
        return this.height;
    }

    private int index(int i, int realSize, int virtualSize) {
        if (i > virtualSize) {
            throw new IndexOutOfBoundsException();
        } else {
            return i % realSize;
        }
    }

    public char get(int x, int y) {
        String row = source[index(y, source.length, height)];
        return row.charAt(index(x, row.length(), width));
    }

    public static DynamicGrid repeatHorizontally(String[] grid) {
        return new DynamicGrid(grid, Integer.MAX_VALUE, grid.length);
    }

}
