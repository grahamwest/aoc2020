package org.grahamwest.aoc2020.day8;

import org.grahamwest.aoc2020.util.PuzzleInput;
import org.grahamwest.aoc2020.vm.Instruction;
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

    private static Program modify(Program prog, int lineNumber) {

        Instruction instruction = prog.get(lineNumber);
        String opcode = instruction.getOpcode();

        if ("jmp".equals(opcode)) {
            instruction = Instruction.from("nop", instruction.getOperands());
        } else if ("nop".equals(opcode)) {
            instruction = Instruction.from("jmp", instruction.getOperands());
        }

        return prog.cloneWithModification(lineNumber, instruction);
    }

    public static int part2() {
        Stream<String> input = PuzzleInput.lines("day8/input.txt");
        Program prog = Program.parse(input);

        for (int i = 0; i < prog.size(); i++) {
            Program modifiedProg = modify(prog, i);
            Machine vm = new Machine();
            vm.load(modifiedProg);

            StringBuilder sb = new StringBuilder();
            int accumulator = vm.runUntil( logger(vm, sb).or(infiniteLoop()) ).getAccumulator();
            if (vm.isHalted()) {
                System.out.println(sb);
                System.out.println("Successfully ran modified program on line " + i);
                return accumulator;
            }
        }

        return -1;
    }

    public static void main(String... args) {
        System.out.println(part1());

        System.out.println(part2());
    }

}