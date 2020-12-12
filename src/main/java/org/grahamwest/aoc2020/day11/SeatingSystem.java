package org.grahamwest.aoc2020.day11;

import org.grahamwest.aoc2020.util.Grid;
import org.grahamwest.aoc2020.util.PuzzleInput;

import java.util.stream.Stream;

public class SeatingSystem {

    private enum Seat {
        EMPTY, OCCUPIED, FLOOR;

        public static Seat from(int c) {
            switch (c) {
                case '#': return OCCUPIED;
                case 'L': return EMPTY;
                case '.': return FLOOR;
            }

            throw new IllegalArgumentException();
        }

        public String toString() {
            if (this == OCCUPIED) return "#";
            if (this == EMPTY) return "L";
            if (this == FLOOR) return ".";
            else return "?";
        }
    }

    private static Grid<Seat> parse(Stream<String> input) {
        return Grid.from( input.map( row -> row.codePoints().mapToObj(Seat::from) ) );
    }


    public static Grid<Seat> applyRules(final Grid<Seat> grid) {
        return grid.transform( (seat, coord) -> {
            int occupied = grid.countNeighbours(coord.getX(), coord.getY(), Seat.OCCUPIED);

            if (seat == Seat.EMPTY && occupied == 0) {
                return Seat.OCCUPIED;
            }

            if (seat == Seat.OCCUPIED && occupied >= 4) {
                return Seat.EMPTY;
            }

            return seat;
        });
    }

    public static int part1(Stream<String> input) {
        Grid<Seat> grid = parse(input);

        Grid<Seat> transformed = applyRules(grid);

        System.out.println(grid);
        System.out.println(transformed);
        System.out.println(transformed.countNeighbours(0,0, Seat.OCCUPIED));

        while ( !transformed.equals(grid) ) {
            grid = transformed;
            transformed = applyRules(transformed);
            System.out.println(transformed);
        }

        return grid.count(Seat.OCCUPIED);
    }

    public static void main(String... args) {
        Stream<String> input = PuzzleInput.lines("day11/input.txt");

        System.out.println( part1(input) );

    }

}
