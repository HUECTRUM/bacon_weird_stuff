package com.bacon.statemachine;

import com.bacon.statemachine.states.GameState;

public enum GameStates implements GameState {
    START {
        @Override
        public GameState nextState() {
            return CHARACTERS_SELECTED;
        }

        @Override
        public void transition(GameInfoHolder holder) {

        }
    },
    CHARACTERS_SELECTED {
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
            return PRIORITY_CHECK;
        }

        @Override
        public void transition(GameInfoHolder holder) {

        }
    },
    PRIORITY_CHECK {
        @Override
        public GameState nextState() {
            return ACTIVE_PLAYER_ATTACK_START;
        }

        @Override
        public void transition(GameInfoHolder holder) {

        }
    },
    ACTIVE_PLAYER_ATTACK_START {
        @Override
        public GameState nextState() {
            return ACTIVE_PLAYER_RANGE_CHECK;
        }

        @Override
        public void transition(GameInfoHolder holder) {

        }
    },
    ACTIVE_PLAYER_RANGE_CHECK {
        @Override
        public GameState nextState() {
            return ACTIVE_PLAYER_DAMAGE;
        }

        @Override
        public void transition(GameInfoHolder holder) {

        }
    },
    ACTIVE_PLAYER_DAMAGE {
        @Override
        public GameState nextState() {
            return null;
        }

        @Override
        public void transition(GameInfoHolder holder) {

        }
    },
    REACTIVE_PLAYER_ATTACK_START {
        @Override
        public GameState nextState() {
            return REACTIVE_PLAYER_RANGE_CHECK;
        }

        @Override
        public void transition(GameInfoHolder holder) {

        }
    },
    REACTIVE_PLAYER_RANGE_CHECK {
        @Override
        public GameState nextState() {
            return REACTIVE_PLAYER_DAMAGE;
        }

        @Override
        public void transition(GameInfoHolder holder) {

        }
    },
    REACTIVE_PLAYER_DAMAGE {
        @Override
        public GameState nextState() {
            return BEAT_START;
        }

        @Override
        public void transition(GameInfoHolder holder) {

        }
    },
    GAME_END {
        @Override
        public GameState nextState() {
            return null; //should never be called
        }

        @Override
        public void transition(GameInfoHolder holder) {

        }
    }
}
