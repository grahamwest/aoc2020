package org.grahamwest.aoc2020.util.graph;

import java.util.*;
import java.util.stream.Collectors;

public class Graph<T> {

    Map<String, Node<T>> index;
    Map<String, Set<String>> invertedIndex;

    public Graph() {
        this.index = new HashMap<>();
        this.invertedIndex = new HashMap<>();
    }

    public Node<T> get(String nodeName) {
        return index.get(nodeName);
    }

    public Set<Node<T>> getParents(String nodeName) {
        return invertedIndex.getOrDefault(nodeName, Set.of()).stream()
                .map(this::get)
                .collect(Collectors.toSet());
    }

    public Node<T> getOrCreate(String nodeName) {
        return getOrCreate(nodeName, Optional.empty());
    }

    public Node<T> getOrCreate(String nodeName, T value) {
        return getOrCreate(nodeName, Optional.ofNullable(value));
    }

    public Node<T> getOrCreate(String nodeName, Optional<T> value) {
        return index.computeIfAbsent(nodeName, k -> new Node<T>(k, value) );
    }

    public synchronized void addEdge(String parentNodeName, String childNodeName, int weight) {
        getOrCreate(parentNodeName).addChild( getOrCreate(childNodeName), weight );
        invertedIndex.computeIfAbsent(childNodeName, k -> new HashSet<>()).add(parentNodeName);
    }

}
