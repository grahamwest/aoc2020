package org.grahamwest.aoc2020.day9;

import org.grahamwest.aoc2020.util.PuzzleInput;

import java.util.stream.LongStream;

public class EncodingError {

    public static int[] isSumOf(long target, long[] history, int n, int length) {
        for (int i = n; i < n + length; i++) {
            long a = history[i];

            long b = 0;
            for (int j = i+1; j < n + length; j++) {
                b = history[j];
                if (a+b == target) {
                    return new int[]{i,j};
                } else if (a+b < target) {
                    a += b;
                } else {
                    break;
                }
            }
        }
        return new int[]{};
    }

    public static long min(long[] values, int i, int j) {
        long x = values[i];
        for (int k = i+1; k <j; k++) {
            if (values[k] < x) {
                x = values[k];
            }
        }

        return x;
    }


    public static long max(long[] values, int i, int j) {
        long x = values[i];
        for (int k = i+1; k <j; k++) {
            if (values[k] > x) {
                x = values[k];
            }
        }

        return x;
    }

    public static long part2(LongStream input) {

        long[] buffer = input.toArray();

        int[] range = isSumOf(1721308972L, buffer, 0, buffer.length);
        if (range.length > 0) {
            long a = min(buffer, range[0], range[1]);
            long b = max(buffer, range[0], range[1]);

            System.out.println(a+b);
            return 0L;
        }

/*
        for (int i = 25; i < buffer.length; i++) {
            int[] range = isSumOf(buffer[i], buffer, i-25, 25);
            if (range.length > 0) {
                System.out.println(min(buffer, range[0], range[1]));
                System.out.println(max(buffer, range[0], range[1]));
                return buffer[i];
            }
        }
*/
        return -1;
    }


    public static void main(String... args) {
        LongStream input = PuzzleInput.linesLong("day9/input.txt");

        System.out.println( part2(input) );
        //System.out.println( part2(prog) );
    }


}
