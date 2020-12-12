package org.grahamwest.aoc2020.util;

public class Arrays {

    public static <T> T last(T[] arr) {
        return arr[arr.length - 1];
    }


    private static <T> int countDiff(T[][] a, T[][] b) {
        int count = 0;
        for (int i = 0; i < a[0].length; i++) {
            for (int j = 0; j < a.length; j++) {
                if (a[i][j] != b[i][j]) count++;
            }
        }

        return count;
    }

    public static <T> String gridToString(T[][] grid) {
        StringBuilder buf = new StringBuilder();

        for (int i = 0; i < grid[0].length; i++) {
            for (int j = 0; j < grid.length; j++) {
                buf.append(grid[i][j]);
            }
            buf.append("\n");
        }

        return buf.toString();
    }

}
