package org.grahamwest.aoc2020.day7;

import org.grahamwest.aoc2020.util.PuzzleInput;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.grahamwest.aoc2020.util.Collections.toList;

public class HandyHaversacks {

    private Stream<BagRelationship> parseRules(Stream<String> input) {
        return input.map(BagRelationship::from);
    }

    private int countParentBags(Stream<String> input) {
        Map<String, Set<String>> childToParentBagColors = new HashMap<>();
        List<BagRelationship> bags = toList(parseRules(input));

        for (BagRelationship rel : bags) {
            for (String child: rel.getChildren().keySet()) {
                childToParentBagColors.computeIfAbsent(child, x -> new HashSet<>()).add(rel.getColor());
            }
        }

        return getParentBags("shiny gold", childToParentBagColors).size();
    }

    private Set<String> getParentBags(String childColor, final Map<String, Set<String>> childToParentBagColors) {

        return childToParentBagColors.getOrDefault(childColor, Set.of()).stream()
                .flatMap( c -> Stream.concat(Stream.of(c), getParentBags(c, childToParentBagColors).stream() ))
                .collect(Collectors.toSet());
    }

    private int countChildBags(Stream<String> input) {
        Map<String, Set<BagRequirement>> parentToChild = new HashMap<>();
        List<BagRelationship> bags = toList(parseRules(input));

        for (BagRelationship rel : bags) {
            Set<BagRequirement> children = rel.getChildren().entrySet().stream()
                    .map( e -> new BagRequirement(e.getKey(), e.getValue()) )
                    .collect(Collectors.toSet());

            parentToChild.put(rel.getColor(), children);
        }

        return countChildBags("shiny gold", parentToChild);
    }

    private int countChildBags(String color, final Map<String, Set<BagRequirement>> parentToChild) {
        return parentToChild.get(color).stream()
                .mapToInt( c -> c.getQuantity() * (1 + (countChildBags(c.getColor(), parentToChild))) )
                .sum();
    }

    public static void main(String... args) {
        Stream<String> input = PuzzleInput.lines("day7/input.txt");
        int numBags = new HandyHaversacks().countChildBags(input);
        System.out.println(numBags);
    }

}
