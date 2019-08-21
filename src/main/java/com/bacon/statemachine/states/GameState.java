package com.bacon.statemachine.states;

import com.bacon.holders.GameInfoHolder;

public interface GameState {
    GameState nextState();

    void transition(GameInfoHolder holder);
}
