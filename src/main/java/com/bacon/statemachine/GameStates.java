package com.bacon.statemachine;

import com.bacon.statemachine.states.GameState;

public enum GameStates implements GameState {
    START {
        @Override
        public GameState nextState() {
            return PLAYER_SETUP_FINISHED;
        }

        @Override
        public void transition(GameInfoHolder holder) {

        }
    },
    PLAYER_SETUP_FINISHED {
        @Override
        public GameState nextState() {
            return BEAT_START;
        }

        @Override
        public void transition(GameInfoHolder holder) {

        }
    },
    BEAT_START {
        @Override
        public GameState nextState() {
            return BEAT_START;
        }

        @Override
        public void transition(GameInfoHolder holder) {

        }
    }
}
