package org.grahamwest.aoc2020.day7;

import org.grahamwest.aoc2020.util.PuzzleInput;
import org.grahamwest.aoc2020.util.graph.Graph;
import org.grahamwest.aoc2020.util.graph.Node;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.grahamwest.aoc2020.util.Collections.toList;

public class HandyHaversacksGraph {

    private Stream<BagRule> parseRules(Stream<String> input) {
        return input.map(BagRule::from);
    }

    public static Graph<String> buildGraph(Stream<BagRule> rules) {
        Graph<String> graph = new Graph<>();

        for (BagRule r : toList(rules)) {
            graph.getOrCreate(r.getColor());
            for (Map.Entry<String, Integer> child : r.getChildren().entrySet()) {
                graph.addEdge( r.getColor(), child.getKey(), child.getValue() );
            }
        }

        return graph;
    }

    private int countParentBags(Stream<String> input) {
        Graph<String> graph = buildGraph(parseRules(input));

        return getParentBags("shiny gold", graph).size();
    }

    private Set<String> getParentBags(String childColor, final Graph<String> graph) {

        return graph.getParents(childColor).stream()
                .flatMap( c -> Stream.concat(Stream.of(c.getName()), getParentBags(c.getName(), graph).stream() ))
                .collect(Collectors.toSet());
    }

    private int countChildBags(Stream<String> input) {
        Graph<String> graph = buildGraph(parseRules(input));

        return countChildBags(graph.get("shiny gold"));
    }

    private int countChildBags(Node<String> node) {
        if (node.getChildren().size() == 0) {
            return 0;
        }

        return node.getChildren().stream()
                .mapToInt( edge -> edge.getWeight() * (1 + (countChildBags(edge.getChild()))) )
                .sum();
    }

    public static void main(String... args) {
        Stream<String> input = PuzzleInput.lines("day7/input.txt");
        int numParents = new HandyHaversacksGraph().countParentBags(input);
        System.out.println(numParents);

        input = PuzzleInput.lines("day7/input.txt");
        int numBags = new HandyHaversacksGraph().countChildBags(input);
        System.out.println(numBags);
    }

}
