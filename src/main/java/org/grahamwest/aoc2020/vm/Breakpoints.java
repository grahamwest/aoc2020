package org.grahamwest.aoc2020.vm;

import java.util.HashSet;
import java.util.Set;
import java.util.function.BiPredicate;

public class Breakpoints {

    public static BiPredicate<State, Instruction> duplicateLines() {
        final Set<Integer> visitedLineNumbers = new HashSet<>();
        return (s,i) -> !visitedLineNumbers.add(s.getPc());
    }

    public static BiPredicate<State, Instruction> logger(final Machine vm) {
        return (s,i) -> {
            System.out.println(vm);
            return false;
        };
    }

    public static BiPredicate<State, Instruction> infiniteLoop() {
        // not doesn't check for state of accumulator so won't work with branch commands
        return duplicateLines().and( (s,i) -> (i != null) && "jmp".equals(i.getOpcode()) );
    }
}
