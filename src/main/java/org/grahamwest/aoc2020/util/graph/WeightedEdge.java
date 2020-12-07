package org.grahamwest.aoc2020.util.graph;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor
public class WeightedEdge<T> {

    Node<T> child;
    int weight;
}
