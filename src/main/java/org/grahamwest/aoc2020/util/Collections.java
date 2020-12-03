package org.grahamwest.aoc2020.util;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Collections {

    public static <T> List<T> clone(List<T> list) {
        List<T> clone = new ArrayList<>(list.size());
        clone.addAll(list);
        return clone;
    }

    public static <T> List<T> removeFirst(List<T> list, T value) {
        int i = list.indexOf(value);
        list.remove(i);
        return list;
    }

    public static <T> List<T> toList(Stream<T> stream) {
        return stream.collect(Collectors.toList());
    }

    public static <T> T[] array(T... values) {
        return values;
    }

}
