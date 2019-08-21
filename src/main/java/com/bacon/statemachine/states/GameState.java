package com.bacon.statemachine.states;

import com.bacon.statemachine.GameInfoHolder;

public interface GameState {
    GameState nextState();

    void transition(GameInfoHolder holder);
}
