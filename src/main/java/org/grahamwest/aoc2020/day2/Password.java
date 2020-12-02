package org.grahamwest.aoc2020.day2;

import java.util.function.Function;

public class Password {

    String password;
    PasswordPolicy policy;

    public Password(String password, PasswordPolicy policy) {
        this.password = password;
        this.policy = policy;
    }

    public boolean isValid() {
        return policy.isValid(password);
    }

    public static Password parseEntry(String entry, Function<String, PasswordPolicy> policyParser) {
        String[] sections = entry.split(": ");
        return new Password(sections[1], policyParser.apply(sections[0]));
    }

}
