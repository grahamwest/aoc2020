package org.grahamwest.aoc2020.day2;

public class ExactlyOnePositionPasswordPolicy implements PasswordPolicy {

    int i;
    int j;
    char c;

    public ExactlyOnePositionPasswordPolicy(int i, int j, char c) {
        this.i = i;
        this.j = j;
        this.c = c;
    }

    @Override
    public boolean isValid(String password) {
        char ic = password.charAt(i - 1);
        char jc = password.charAt(j - 1);
        return (ic == c ^ jc == c);
    }

    public static ExactlyOnePositionPasswordPolicy parse(String policyStr) {
        String[] sections = policyStr.split("-| ");
        return new ExactlyOnePositionPasswordPolicy(Integer.valueOf(sections[0]), Integer.valueOf(sections[1]), sections[2].charAt(0));
    }

}
