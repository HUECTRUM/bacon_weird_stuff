package com.bacon.statemachine.states;

import com.bacon.holders.GameInfoHolder;
import com.bacon.statemachine.conditions.StateTransitionCondition;

import java.util.Map;

public interface GameState {
    Map<StateTransitionCondition, GameState> nextStates();

    StateTransitionCondition transition(GameInfoHolder holder);
}
