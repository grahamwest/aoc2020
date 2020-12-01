package org.grahamwest.aoc2020.day1;

import org.grahamwest.aoc2020.util.Collections;
import org.grahamwest.aoc2020.util.PuzzleInput;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ReportRepair {

    private Optional<Integer> findMultipleOf(List<Integer> numbers, int target, int numberOfTerms) {

        if (numberOfTerms < 1) {
            throw new RuntimeException("Require at least 1 term");
        }
        else if (numberOfTerms == 1) {
            if (numbers.contains(target)) {
                return Optional.of(target);
            } else {
                return Optional.empty();
            }
        } else {

            for (int n : numbers) {
                int diffTarget = target - n;

                List<Integer> clone = Collections.removeFirst(Collections.clone(numbers), n);

                Optional<Integer> multiple = findMultipleOf(clone, diffTarget, numberOfTerms - 1);
                if (multiple.isPresent()) {
                    return multiple.map( v -> v * n );
                }
            }

        }

        return Optional.empty();

    }

    public int multipleOf2020Entries(int[] expenses) {

        List<Integer> expensesSet = new ArrayList<>(expenses.length);
        for (int n : expenses) {
            expensesSet.add(n);
        }

        return findMultipleOf(expensesSet, 2020, 3).get();
    }

    public static void main(String... args) {
        int[] input = PuzzleInput.linesInt("day1/expenses.txt").toArray();
        int multiple = new ReportRepair().multipleOf2020Entries(input);
        System.out.println(multiple);
    }

}
