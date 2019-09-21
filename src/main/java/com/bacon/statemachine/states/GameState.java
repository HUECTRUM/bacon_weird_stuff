package com.bacon.statemachine.states;

import com.bacon.holders.GameInfoHolder;
import com.bacon.statemachine.GameStates;
import com.bacon.statemachine.conditions.StateTransitionCondition;

import java.util.Map;

public interface GameState {
    Map<StateTransitionCondition, GameStates> nextStates();

    StateTransitionCondition transitionInternal(GameInfoHolder holder);
}
