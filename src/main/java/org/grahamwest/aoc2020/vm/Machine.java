package org.grahamwest.aoc2020.vm;

import lombok.ToString;

import java.util.function.BiPredicate;
import java.util.function.Predicate;

@ToString
public class Machine {

    State state;

    @ToString.Exclude
    Program program;

    public Machine() {
        this.state = new State();
    }

    public void load(Program program) {
        this.program = program;
    }

    public State step() {
        if (state.pc >= program.size()) {
            this.state.halt();
        }

        if (state.isHalted()) {
            return this.state;
        }

        program.get(state.pc).execute(state);
        return state;
    }

    public State runUntilHalt() {
        return runUntil( (s, i) -> s.isHalted() );
    }

    public State runUntil(BiPredicate<State, Instruction> observer) {
        while ( !this.state.isHalted && !observer.test(this.state, program.get(state.pc))) {
            step();
        }

        return this.state;
    }

}
