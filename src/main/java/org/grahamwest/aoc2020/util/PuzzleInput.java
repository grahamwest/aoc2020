package org.grahamwest.aoc2020.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.grahamwest.aoc2020.util.Collections.toList;

public class PuzzleInput {

    public static Stream<String> lines(String fileName) {
        try {
            return Files.lines(Path.of("src/main/resources/" + fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Stream<List<String>> groupedLines(String fileName) {

        List<List<String>> groups = new ArrayList<>();
        List<String> group = new ArrayList<>();

        for (String l : toList(lines(fileName))) {
            if (l.length() == 0) {
                groups.add(group);
                group = new ArrayList<>();
            } else {
                group.add(l);
            }
        }

        groups.add(group);
        return groups.stream();
    }

    public static IntStream linesInt(String fileName) {
        return lines(fileName).mapToInt(Integer::valueOf);
    }

}
