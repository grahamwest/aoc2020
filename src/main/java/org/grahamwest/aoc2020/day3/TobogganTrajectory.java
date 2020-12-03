package org.grahamwest.aoc2020.day3;

import static org.grahamwest.aoc2020.util.Collections.*;
import org.grahamwest.aoc2020.util.DynamicGrid;
import org.grahamwest.aoc2020.util.PuzzleInput;

import java.util.stream.Stream;

public class TobogganTrajectory {

    private DynamicGrid grid(Stream<String> map) {
        return DynamicGrid.repeatHorizontally(map.toArray(String[]::new));
    }

    public int countTrees(DynamicGrid grid, int x, int y) {
        int i = 0;
        int j = 0;
        int trees = 0;

        while (j < grid.getHeight()) {
            char c = grid.get(i, j);
            System.out.println(i + "," + j + " -> " + c);
            if (c == '#') {
                trees++;
            }
            i += x;
            j += y;
        }

        return trees;
    }

    public long multiplyAllRoutes(Stream<String> input) {

        DynamicGrid grid = grid(input);

        Integer[][] slopes = array(
                array(1,1),
                array(3,1),
                array(5, 1),
                array(7, 1),
                array(1,2)
        );

        long multiply = 1;
        for (Integer[] coord : slopes) {
            multiply *= countTrees(grid, coord[0], coord[1]);
        }
        return multiply;
    }

    public static void main(String... args) {
        Stream<String> input = PuzzleInput.lines("day3/input.txt");
        long numTrees = new TobogganTrajectory().multiplyAllRoutes(input);//.countTrees(input, 3, 1);
        System.out.println(numTrees);
    }

}
