package org.grahamwest.aoc2020.day2;

import org.grahamwest.aoc2020.util.PuzzleInput;

import java.util.function.Function;
import java.util.stream.Stream;

public class PasswordPhilosophy {

    public int countValid(Stream<String> passwords, Function<String, PasswordPolicy> policyParser) {
        return (int) passwords.map( s -> Password.parseEntry(s, policyParser) ).filter(Password::isValid).count();
    }

    public static void main(String... args) {
        Stream<String> input = PuzzleInput.lines("day2/input.txt");
        int numValidPasswords = new PasswordPhilosophy().countValid(input, ExactlyOnePositionPasswordPolicy::parse);
        System.out.println(numValidPasswords);
    }

}
