package org.grahamwest.aoc2020.util.graph;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class Node<T> {

    String name;
    Optional<T> value;
    Set<WeightedEdge> children;

    public Node(String name, Optional<T> value) {
        this.name = name;
        this.value = value;
        this.children = new HashSet<>();
    }

    Node from(String name, Optional<T> value) {
        return new Node<T>(name, value);
    }

    protected void addChild(Node child, int weight) {
        this.children.add(new WeightedEdge<T>(child, weight));
    }

    public Set<String> getChildNames() {
        return children.stream()
                .map( n -> n.getChild().getName() )
                .collect(Collectors.toSet());
    }
}
