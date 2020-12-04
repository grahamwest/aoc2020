package org.grahamwest.aoc2020.day4;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;
import static org.grahamwest.aoc2020.util.Numbers.*;

public class Passport {

    private static final Map<String, Predicate<String>> requiredFieldsRegex = Map.of(
            "byr", (str -> within(str, 1920, 2002)),
            "iyr", (str -> within(str, 2010, 2020)),
            "eyr", (str -> within(str, 2020, 2030)),
            "hgt", (str -> {
                String dig = str.substring(0, Math.max(0,str.length()-2));
                String units = str.substring(Math.max(0,str.length()-2));
                return ("cm".equals(units) && within(dig, 150, 193)) ||
                        ("in".equals(units) && within(dig, 59, 76));
            }),
            "hcl", (str -> str.matches("#[0-9a-f]{6}")),
            "ecl", (str -> str.matches("amb|blu|brn|gry|grn|hzl|oth")),
            "pid", (str -> str.matches("[0-9]{9}"))
    );

    private Map<String, String> fields;

    private Passport(Map<String, String> fields) {
        this.fields = fields;
    }

    public boolean isValid() {

        return requiredFieldsRegex.entrySet().stream()
                .map( e -> e.getValue().test(this.fields.getOrDefault(e.getKey(), "")) )
                .allMatch(b -> b == true);

    }

    public static Passport.Builder builder() {
        return new Passport.Builder();
    }

    static class Builder {

        Map<String, String> fields;
        private Builder() {
            fields = new HashMap<>();
        }

        public Builder addField(String name, String value) {
            this.fields.put(name, value);
            return this;
        }

        public Passport build() {
            return new Passport(this.fields);
        }

    }

}
