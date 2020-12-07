package org.grahamwest.aoc2020.day7;

import org.grahamwest.aoc2020.day4.Passport;
import org.grahamwest.aoc2020.util.Numbers;

import java.util.HashMap;
import java.util.Map;

public class BagRelationship {

    private String color;
    private Map<String, Integer> children;

    private BagRelationship(String color, Map<String, Integer> children) {
        this.color = color;
        this.children = children;
    }

    public Map<String, Integer> getChildren() {
        return children;
    }

    public String getColor() {
        return color;
    }

    public static BagRelationship from(String rule) {

        String[] bagContains = rule.split(" bags contain ");
        String color = bagContains[0];

        if ("no other bags.".equals(bagContains[1])) {
            return new BagRelationship(color, Map.of());
        }

        String[] children = bagContains[1].replaceAll("(\\.| bags?)", "").split(", ");
        Map<String, Integer> childBagCount = new HashMap<>();
        for (String child : children) {
            childBagCount.put(child.substring(2), Integer.valueOf(child.substring(0,1)));
        }

        return new BagRelationship(color, childBagCount);
    }
}
