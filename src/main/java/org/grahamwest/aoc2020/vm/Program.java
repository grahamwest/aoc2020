package org.grahamwest.aoc2020.vm;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Program {

    List<Instruction> instructions;

    public static Program parse(Stream<String> lines) {
        return new Program( lines
                .map( l -> l.split(" "))
                .map(Instruction::from)
                .collect(Collectors.toList())
        );
    }

    public Instruction get(int lineNumber) {
        if (lineNumber >= instructions.size()) {
            return null;
        }
        return this.instructions.get(lineNumber);
    }

    public int size() {
        return this.instructions.size();
    }

}
