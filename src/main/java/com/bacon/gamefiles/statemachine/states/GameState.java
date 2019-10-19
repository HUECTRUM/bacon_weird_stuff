package com.bacon.gamefiles.statemachine.states;

import com.bacon.gamefiles.holders.GameInfoHolder;
import com.bacon.gamefiles.statemachine.GameStates;
import com.bacon.gamefiles.statemachine.conditions.StateTransitionCondition;

import java.util.Map;

public interface GameState {
    Map<StateTransitionCondition, GameStates> nextStates();

    StateTransitionCondition transitionInternal(GameInfoHolder holder);
}
