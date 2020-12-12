package org.grahamwest.aoc2020.day12;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class NavigationInstruction {

    char command;
    int value;

    public static NavigationInstruction from(String str) {
        return new NavigationInstruction(str.charAt(0), Integer.valueOf(str.substring(1)));
    }

}
