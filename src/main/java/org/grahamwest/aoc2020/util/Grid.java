package org.grahamwest.aoc2020.util;

import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.grahamwest.aoc2020.util.Collections.toList;
import static org.grahamwest.aoc2020.util.Numbers.clamp;

@EqualsAndHashCode
public class Grid<T> {

    final private List<List<T>> data;

    private Grid(List<List<T>> data) {
        this.data = data;
    }

    public static <T> Grid<T> from(Stream<Stream<T>> input) {
        return new Grid<>(toList(input.map(Collections::toList)));
    }

    public int width() {
        return data.get(0).size();
    }

    public int height() {
        return data.size();
    }

    public T get(int x, int y) {
        return data.get(y).get(x);
    }

    public Stream<Stream<T>> streamRows() {
        return data.stream().map( List::stream );
    }

    public Stream<T> stream() {
        return streamRows().flatMap( Function.identity() );
    }

    public int countNeighbours(int x, int y, T equals) {
        return transformNeighbours( (v,c) -> Objects.equals(v, equals), x, y).stream()
                .mapToInt(b -> (b != null && b) ? 1 : 0)
                .sum();
    }

    public int count(T equals) {
        return transform( (v,c) -> Objects.equals(v, equals)).stream()
                .mapToInt(b -> b ? 1 : 0)
                .sum();
    }

    public Grid<Boolean> diff(Grid<T> comparison) {
        return this.transform( (v, coord) -> Objects.equals( v, comparison.get(coord.getX(), coord.getY()) ) );
    }

    public int countDiff(Grid<T> comparison) {
        return diff(comparison).stream()
                .mapToInt(b -> b ? 1 : 0)
                .sum();
    }

    public <R> Grid<R> transform(BiFunction<T, Coordinate, R> fn) {
        return transform(fn, 0, 0, width(), height());
    }

    public <R> Grid<R> transformNeighbours(BiFunction<T, Coordinate, R> fn, int x, int y) {
        BiFunction<T, Coordinate, R> ignoreOrigin = (value, coord) -> coord.equals(x,y) ? null : fn.apply(value, coord);
        return transform(ignoreOrigin, x-1, y-1, 3, 3);
    }

    public <R> Grid<R> transform(BiFunction<T, Coordinate, R> fn, int x, int y, int width, int height) {
        List<List<R>> data = new ArrayList<>();
        for (int j = clamp(y, 0, height() - 1); j < clamp(y + height, 0, height()); j++) {

            List<R> row = new ArrayList<>();
            for (int i = clamp(x, 0, width() - 1); i < clamp(x + width, 0, width()); i++) {
                row.add( fn.apply(get(i,j), Coordinate.from(i, j)) );
            }
            data.add(row);
        }
        return new Grid<>(data);
    }

    public String toString() {
        return data.stream()
                .flatMap( row -> Stream.concat(row.stream().map( v -> v.toString()), Stream.of("\n")))
                .collect(Collectors.joining());
    }

}
