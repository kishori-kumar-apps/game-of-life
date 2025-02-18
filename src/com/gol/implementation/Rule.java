package com.gol.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;

/**
 * Rules to be applied for the game of life
 * */
public class Rule {
    private final List<BiFunction<Integer, GOLEnum, Optional<GOLEnum>>> ruleList;

    public Rule() {
        ruleList = new ArrayList<>();

        // Add underpopulation rule
        BiFunction<Integer, GOLEnum, Optional<GOLEnum>> rule = (liveNeighboursCount, currentState) -> {
            if(currentState == GOLEnum.ALIVE && liveNeighboursCount < 2) {
                return Optional.of(GOLEnum.DEAD);
            }

            return Optional.empty();
        };
        ruleList.add(rule);

        // Live to next generation rule
        rule = (liveNeighboursCount, currentState) -> {
            if(currentState == GOLEnum.ALIVE &&
                    (liveNeighboursCount == 2 || liveNeighboursCount == 3)) {
                return Optional.of(currentState);
            }

            return Optional.empty();
        };
        ruleList.add(rule);

        // Overcrowding rule
        rule = (liveNeighboursCount, currentState) -> {
            if(currentState == GOLEnum.ALIVE && liveNeighboursCount > 2) {
                return Optional.of(GOLEnum.DEAD);
            }

            return Optional.empty();
        };
        ruleList.add(rule);

        // Reproduction rule
        rule = (liveNeighboursCount, currentState) -> {
            if(currentState == GOLEnum.DEAD && liveNeighboursCount == 3) {
                return Optional.of(GOLEnum.ALIVE);
            }

            return Optional.empty();
        };
        ruleList.add(rule);
    }

    public GOLEnum applyRulesAndGetCurrentState(int liveNeighboursCount, GOLEnum currentState) {
        for (BiFunction<Integer, GOLEnum, Optional<GOLEnum>> rule : ruleList) {
            Optional<GOLEnum> stateAfterRuleApplied = rule.apply(liveNeighboursCount, currentState);
            if (stateAfterRuleApplied.isPresent()) {
                return stateAfterRuleApplied.get();
            }
        }

        return currentState;
    }
}
