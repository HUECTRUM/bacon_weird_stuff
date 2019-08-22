package com.bacon.statemachine;

import com.bacon.holders.GameInfoHolder;
import com.bacon.statemachine.conditions.StateTransitionCondition;
import com.bacon.statemachine.states.GameState;

import java.util.Map;

import static com.bacon.statemachine.conditions.RegularTransitionConditions.EMPTY;
import static java.util.Map.of;

public enum GameStates implements GameState {
    START {
        @Override
        public Map<StateTransitionCondition, GameState> nextStates() {
            return of(EMPTY, CHARACTERS_SELECTED);
        }

        @Override
        public void transition(GameInfoHolder holder) {
            holder.resolversContainer.characterSelectionResolver.selectPlayers(holder);
        }
    },
    CHARACTERS_SELECTED {
        @Override
        public Map<StateTransitionCondition, GameState> nextStates() {
            return of(EMPTY, PLAYER_SETUP_FINISHED);
        }

        @Override
        public void transition(GameInfoHolder holder) {
            holder.resolversContainer.discardResolver.selectDiscards(holder);
        }
    },
    PLAYER_SETUP_FINISHED {
        @Override
        public Map<StateTransitionCondition, GameState> nextStates() {
            return of(EMPTY, BEAT_START);
        }

        @Override
        public void transition(GameInfoHolder holder) {

        }
    },
    BEAT_START {
        @Override
        public Map<StateTransitionCondition, GameState> nextStates() {
            return of(EMPTY, PRIORITY_CHECK);
        }

        @Override
        public void transition(GameInfoHolder holder) {
            holder.resolversContainer.pairSelectionResolver.selectPairs(holder);
        }
    },
    PRIORITY_CHECK {
        @Override
        public Map<StateTransitionCondition, GameState> nextStates() {
            return of(EMPTY, ACTIVE_PLAYER_ATTACK_START);
        }

        @Override
        public void transition(GameInfoHolder holder) {
            holder.resolversContainer.priorityResolver.resolvePriority(holder);
        }
    },
    ACTIVE_PLAYER_ATTACK_START {
        @Override
        public Map<StateTransitionCondition, GameState> nextStates() {
            return of(EMPTY, ACTIVE_PLAYER_RANGE_CHECK);
        }

        @Override
        public void transition(GameInfoHolder holder) {

        }
    },
    ACTIVE_PLAYER_RANGE_CHECK {
        @Override
        public Map<StateTransitionCondition, GameState> nextStates() {
            return of(EMPTY, ACTIVE_PLAYER_DAMAGE);
        }

        @Override
        public void transition(GameInfoHolder holder) {
            holder.resolversContainer.rangeChecker.checkRange(holder, true);
        }
    },
    ACTIVE_PLAYER_DAMAGE {
        @Override
        public Map<StateTransitionCondition, GameState> nextStates() {
            return of(EMPTY, REACTIVE_PLAYER_ATTACK_START);
        }

        @Override
        public void transition(GameInfoHolder holder) {
            holder.resolversContainer.damageResolver.resolveDamage(holder, true);
        }
    },
    REACTIVE_PLAYER_ATTACK_START {
        @Override
        public Map<StateTransitionCondition, GameState> nextStates() {
            return of(EMPTY, REACTIVE_PLAYER_RANGE_CHECK);
        }

        @Override
        public void transition(GameInfoHolder holder) {

        }
    },
    REACTIVE_PLAYER_RANGE_CHECK {
        @Override
        public Map<StateTransitionCondition, GameState> nextStates() {
            return of(EMPTY, REACTIVE_PLAYER_DAMAGE);
        }

        @Override
        public void transition(GameInfoHolder holder) {
            holder.resolversContainer.rangeChecker.checkRange(holder, false);
        }
    },
    REACTIVE_PLAYER_DAMAGE {
        @Override
        public Map<StateTransitionCondition, GameState> nextStates() {
            return of(EMPTY, RECYCLE);
        }

        @Override
        public void transition(GameInfoHolder holder) {
            holder.resolversContainer.damageResolver.resolveDamage(holder, false);
        }
    },
    RECYCLE {
        @Override
        public Map<StateTransitionCondition, GameState> nextStates() {
            return of(EMPTY, BEAT_START);
        }

        @Override
        public void transition(GameInfoHolder holder) {
            holder.resolversContainer.recycler.recycle(holder);
        }
    },
    GAME_END {
        @Override
        public Map<StateTransitionCondition, GameState> nextStates() {
            return of();
        }

        @Override
        public void transition(GameInfoHolder holder) {

        }
    }
}
