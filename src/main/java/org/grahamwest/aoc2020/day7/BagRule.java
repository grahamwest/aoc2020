package org.grahamwest.aoc2020.day7;

import java.util.HashMap;
import java.util.Map;

public class BagRule {

    private String color;
    private Map<String, Integer> children;

    private BagRule(String color, Map<String, Integer> children) {
        this.color = color;
        this.children = children;
    }

    public Map<String, Integer> getChildren() {
        return children;
    }

    public String getColor() {
        return color;
    }

    public static BagRule from(String rule) {

        String[] bagContains = rule.split(" bags contain ");
        String color = bagContains[0];

        if ("no other bags.".equals(bagContains[1])) {
            return new BagRule(color, Map.of());
        }

        String[] children = bagContains[1].replaceAll("(\\.| bags?)", "").split(", ");
        Map<String, Integer> childBagCount = new HashMap<>();
        for (String child : children) {
            childBagCount.put(child.substring(2), Integer.valueOf(child.substring(0,1)));
        }

        return new BagRule(color, childBagCount);
    }
}
