package org.grahamwest.aoc2020.day12;

import org.grahamwest.aoc2020.util.Coordinate;
import org.grahamwest.aoc2020.util.PuzzleInput;

import java.util.List;
import java.util.stream.Stream;

import static org.grahamwest.aoc2020.util.Collections.toList;

public class RainRisk {

    public static int part1(Stream<String> input) {

        List<NavigationInstruction> instructions = toList(input.map(NavigationInstruction::from));

        Boat b = new Boat(Coordinate.from(0,0), Boat.EAST);
        for (NavigationInstruction inst : instructions) {
            System.out.println(inst + " | " + b + " => " + b.execute(inst));
            b = b.execute(inst);
        }

        return b.getLocation().manhattan();
    }

    public static int part2(Stream<String> input) {

        List<NavigationInstruction> instructions = toList(input.map(NavigationInstruction::from));

        WaypointBoat b = new WaypointBoat(Coordinate.from(0,0), WaypointBoat.EAST.multiply(10).add(WaypointBoat.NORTH));
        for (NavigationInstruction inst : instructions) {
            System.out.println(inst + " | " + b + " => " + b.execute(inst));
            b = b.execute(inst);
        }

        return b.getLocation().manhattan();
    }

    public static void main(String... args) {
        Stream<String> input = PuzzleInput.lines("day12/input.txt");

        System.out.println( part2(input) );

    }

}
