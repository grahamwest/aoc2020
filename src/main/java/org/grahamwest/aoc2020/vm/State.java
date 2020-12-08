package org.grahamwest.aoc2020.vm;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @ToString
public class State {
    @Setter
    int pc = 0;
    boolean isHalted = false;

    int accumulator = 0;

    public State acc(int n) {
        this.accumulator += n;
        return this;
    }

    public State halt() {
        this.isHalted = true;
        return this;
    }

    public State next() {
        this.pc++;
        return this;
    }
}