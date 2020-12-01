package org.grahamwest.aoc2020.util;

import java.util.ArrayList;
import java.util.List;

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

}
