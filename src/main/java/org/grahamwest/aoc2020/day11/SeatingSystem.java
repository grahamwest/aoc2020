package org.grahamwest.aoc2020.day11;

import org.grahamwest.aoc2020.util.Coordinate;
import org.grahamwest.aoc2020.util.Grid;
import org.grahamwest.aoc2020.util.MutableCoordinate;
import org.grahamwest.aoc2020.util.PuzzleInput;

import java.util.function.Function;
import java.util.function.Supplier;
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

    private static Supplier<Coordinate> dir(Coordinate start, final int x, final int y) {
        MutableCoordinate coord = MutableCoordinate.from(start);
        return () -> {
            coord.set( coord.getX() + x, coord.getY() + y);
            return coord.immutable();
        };
    }

    public static Grid<Seat> applyRulesPart2(final Grid<Seat> grid) {
        return grid.transform( (seat, coord) -> {
            int occupied = (int) grid.find(
                    (s, c) -> s == Seat.OCCUPIED || s == Seat.EMPTY,
                    dir(coord, 0, -1),
                    dir(coord, 1, -1),
                    dir(coord, 1, 0),
                    dir(coord, 1, 1),
                    dir(coord, 0, 1),
                    dir(coord, -1, 1),
                    dir(coord, -1, 0),
                    dir(coord, -1, -1)
            ).filter( s -> s == Seat.OCCUPIED ).count();

            if (seat == Seat.EMPTY && occupied == 0) {
                return Seat.OCCUPIED;
            }

            if (seat == Seat.OCCUPIED && occupied >= 5) {
                return Seat.EMPTY;
            }

            return seat;
        });
    }

    public static int countOccupiedWhenStable(Stream<String> input, Function<Grid<Seat>, Grid<Seat>> rules) {
        Grid<Seat> grid = parse(input);

        Grid<Seat> transformed = rules.apply(grid);

        while ( !transformed.equals(grid) ) {
            System.out.println(grid);
            grid = transformed;
            transformed = rules.apply(transformed);
        }

        return grid.count(Seat.OCCUPIED);
    }

    public static void main(String... args) {
        Stream<String> input = PuzzleInput.lines("day11/input.txt");

        System.out.println( countOccupiedWhenStable(input, SeatingSystem::applyRulesPart2) );

    }

}
