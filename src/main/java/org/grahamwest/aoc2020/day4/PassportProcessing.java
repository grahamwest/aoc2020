package org.grahamwest.aoc2020.day4;

import org.grahamwest.aoc2020.util.Collections;
import org.grahamwest.aoc2020.util.PuzzleInput;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class PassportProcessing {

    private int countValid(Stream<String> input) {
        return (int) parsePassports(input).filter(Passport::isValid).count();
    }

    private Stream<Passport> parsePassports(Stream<String> input) {
        List<Passport> passports = new ArrayList<>();

        Passport.Builder b = Passport.builder();

        for (String s : Collections.toList(input)) {
            if (s.length() == 0) {
                passports.add(b.build());
                b = Passport.builder();
            } else {
                String[] fields = s.split(":| ");
                for (int i = 0; i < fields.length; i++) {
                    b.addField(fields[i], fields[++i]);
                }
            }
        }
        passports.add(b.build());

        return passports.stream();
    }

    public static void main(String... args) {
        Stream<String> input = PuzzleInput.lines("day4/input.txt");
        int validPassportsCount = new PassportProcessing().countValid(input);
        System.out.println(validPassportsCount);
    }

}
