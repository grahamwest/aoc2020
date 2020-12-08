package org.grahamwest.aoc2020.day8;

import org.grahamwest.aoc2020.util.PuzzleInput;
import org.grahamwest.aoc2020.vm.Machine;
import org.grahamwest.aoc2020.vm.Program;

import java.util.stream.Stream;

import static org.grahamwest.aoc2020.vm.Breakpoints.*;

public class HandheldHalting {

    public static int part1() {
        Stream<String> input = PuzzleInput.lines("day8/input.txt");
        Program prog = Program.parse(input);

        Machine vm = new Machine();
        vm.load(prog);

        return vm.runUntil( duplicateLines() ).getAccumulator();
    }

    public static int part2() {
        Stream<String> input = PuzzleInput.lines("day8/input.txt");
        Program prog = Program.parse(input);

        Machine vm = new Machine();
        vm.load(prog);

        return vm.runUntil( logger(vm).or(infiniteLoop()) ).getAccumulator();
    }

    public static void main(String... args) {
        System.out.println(part1());

        //System.out.println(part2());
    }

}