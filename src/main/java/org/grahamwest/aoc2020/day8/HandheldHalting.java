package org.grahamwest.aoc2020.day8;

import org.grahamwest.aoc2020.util.PuzzleInput;
import org.grahamwest.aoc2020.vm.Instruction;
import org.grahamwest.aoc2020.vm.Machine;
import org.grahamwest.aoc2020.vm.Program;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.stream.Stream;

import static org.grahamwest.aoc2020.vm.Breakpoints.*;

public class HandheldHalting {

    public static int part1(Program prog) {
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

    public static int part2(Program prog) {

        for (int i = 0; i < prog.size(); i++) {
            Program modifiedProg = modify(prog, i);
            Machine vm = new Machine();
            vm.load(modifiedProg);

            ByteArrayOutputStream buf = new ByteArrayOutputStream();
            int accumulator = vm.runUntil( logger(new PrintStream(buf)).or(infiniteLoop()) ).getAccumulator();
            if (vm.isHalted()) {
                System.out.println(buf);
                System.out.println("Successfully ran modified program on line " + i);
                return accumulator;
            }
        }

        return -1;
    }

    public static void main(String... args) {
        Stream<String> input = PuzzleInput.lines("day8/input.txt");
        Program prog = Program.parse(input);

        System.out.println( part1(prog) );
        System.out.println( part2(prog) );
    }

}