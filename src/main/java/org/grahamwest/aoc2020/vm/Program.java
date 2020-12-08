package org.grahamwest.aoc2020.vm;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
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

    public Program cloneWithModification(int index, Instruction replacement) {

        List<Instruction> clone = new ArrayList<>(instructions.size());
        for (int i = 0; i < instructions.size(); i++) {

            if (i == index) {
                clone.add(i, replacement);
            } else {
                clone.add(i, instructions.get(i));
            }

        }

        return new Program(clone);
    }

}
