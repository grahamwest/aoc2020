package org.grahamwest.aoc2020.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class PuzzleInput {

    public static Stream<String> lines(String fileName) {
        try {
            return Files.lines(Path.of("src/main/resources/" + fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static IntStream linesInt(String fileName) {
        return lines(fileName).mapToInt(Integer::valueOf);
    }

}
