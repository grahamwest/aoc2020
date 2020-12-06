package org.grahamwest.aoc2020.day6;

import org.grahamwest.aoc2020.util.PuzzleInput;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CustomCustoms {

    private int sumUniqueInGroup(Stream<List<String>> input) {

        return input
                .mapToInt( g -> g.stream()
                        .flatMap(s -> Arrays.stream(s.split("").clone()))
                        .collect(Collectors.toSet()).size()
                )
                .sum();

    }

    private int sumPresentEveryRowInGroup(Stream<List<String>> input) {
        return input.mapToInt( CustomCustoms::countPresentInEveryRow ).sum();
    }

    private static int countPresentInEveryRow(List<String> strings) {
        final int numInGroup = strings.size();
        int[] count = new int[26];

        for (String l : strings) {
            l.chars().forEach( c -> count[c - 'a']++);
        }

        return (int) Arrays.stream(count).filter( c -> c==numInGroup ).count();
    }

    public static void main(String... args) {
        Stream<List<String>> input = PuzzleInput.groupedLines("day6/input.txt");
        int sum = new CustomCustoms().sumPresentEveryRowInGroup(input);
        System.out.println(sum);
    }

}
