package org.grahamwest.aoc2020.vm;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class Instruction {

    private static final Map<String, BiConsumer<State, Instruction>> implementations = Map.of(
            "acc", (state, inst) -> state.acc(inst.getInt(0)).next(),
            "jmp", (state, inst) -> state.setPc(state.getPc() + inst.getInt(0) ),
            "nop", (state, inst) -> state.next()
    );

    String opcode;
    List<String> operands;
    BiConsumer<State, Instruction> executor;

    public static Instruction from(String[] op) {
        return from(op[0], List.of(op[1]));
    }

    public static Instruction from(String opcode, String operand) {
        return from(opcode, List.of(operand));
    }

    public static Instruction from(String opcode, List<String> operands) {
        BiConsumer<State, Instruction> impl = implementations.get(opcode);
        return new Instruction(opcode, operands, impl);
    }

    public int getInt(int index) {
        return Integer.valueOf(this.operands.get(index));
    }

    public void execute(State state) {
        this.executor.accept(state, this);
    }
}
