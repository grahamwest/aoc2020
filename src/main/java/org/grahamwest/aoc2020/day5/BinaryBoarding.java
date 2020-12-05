package org.grahamwest.aoc2020.day5;

import org.grahamwest.aoc2020.util.PuzzleInput;

import java.util.stream.Stream;

public class BinaryBoarding {

    private static int fromBinary(String binary) {
        return Integer.parseInt(binary, 2);
    }

    private static int column(String column) {
        String bin = column.replace('L', '0').replace('R', '1');
        return fromBinary(bin);
    }

    private static int row(String row) {
        String bin = row.replace('F', '0').replace('B', '1');
        return fromBinary(bin);
    }

    private static int toSeatId(String bpCode) {
        return (row(bpCode.substring(0, 7)) * 8) + column(bpCode.substring(7));
    }

    public int missingSeatId(Stream<String> boardingPasses) {
        int[] seatIds = boardingPasses.mapToInt(BinaryBoarding::toSeatId).sorted().toArray();

        int prevSeatId = seatIds[0];
        for (int i = 1; i < seatIds.length; i++) {
            if (seatIds[i] != prevSeatId + 1) {
                return prevSeatId + 1;
            } else {
                prevSeatId = seatIds[i];
            }
        }

        return -1;
    }

    public int maxSeatId(Stream<String> boardingPasses) {

        return boardingPasses.mapToInt(BinaryBoarding::toSeatId).max().getAsInt();

    }

    public static void main(String... args) {
        Stream<String> input = PuzzleInput.lines("day5/input.txt");
        int missingSeatId = new BinaryBoarding().missingSeatId(input);
        System.out.println(missingSeatId);
    }

}