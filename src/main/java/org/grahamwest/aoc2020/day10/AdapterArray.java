package org.grahamwest.aoc2020.day10;

import org.grahamwest.aoc2020.util.PuzzleInput;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class AdapterArray {

    public static int part1(IntStream input) {

        int[] sorted = input.sorted().toArray();

        int diff1 = 1;
        int diff3 = 1;

        for (int i = 1; i < sorted.length; i++) {
            int diff = sorted[i] - sorted[i-1];
            if (diff == 1) diff1++;
            if (diff == 3) diff3++;
        }

        return diff1 * diff3;
    }

    private static Map<Integer,Long> cache = new HashMap<>();
    public static long countPaths(int[] arr, int index) {
        if (cache.containsKey(index)) {
            return cache.get(index);
        }

        if (index == arr.length - 1) {
            return 1;
        }

        long count = 0;
        for (int i = 1; i <= 3; i++) {
            if (index + i >= arr.length) {
                break;
            }
            if (arr[index] + 3 >= arr[index + i]) {
                count += countPaths(arr, index + i);
            }
        }

        cache.put(index, count);

        return count;
    }

    public static long part2(IntStream input) {
        int[] sorted = IntStream.concat(IntStream.of(0), input).sorted().toArray();

        return countPaths(sorted, 0);
    }

    public static void main(String... args) {
        IntStream input = PuzzleInput.linesInt("day10/input.txt");

        //System.out.println( part1(input) );
        System.out.println( part2(input) );

    }

}
