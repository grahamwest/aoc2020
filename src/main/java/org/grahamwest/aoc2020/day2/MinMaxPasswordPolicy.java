package org.grahamwest.aoc2020.day2;

public class MinMaxPasswordPolicy implements PasswordPolicy {

    int min;
    int max;
    char c;

    public MinMaxPasswordPolicy(int min, int max, char c) {
        this.min = min;
        this.max = max;
        this.c = c;
    }

    @Override
    public boolean isValid(String password) {
        int charCount = (int) password.codePoints().filter( i -> i == c ).count();
        return charCount >= min && charCount <= max;
    }

    public static MinMaxPasswordPolicy parse(String policyStr) {
        String[] sections = policyStr.split("-| ");
        return new MinMaxPasswordPolicy(Integer.valueOf(sections[0]), Integer.valueOf(sections[1]), sections[2].charAt(0));
    }

}
