package com.bacon.statemachine;

import com.bacon.gameobjects.triggers.EffectTrigger;
import com.bacon.holders.GameInfoHolder;
import com.bacon.statemachine.conditions.StateTransitionCondition;
import com.bacon.statemachine.states.GameState;

import java.util.Map;

import static com.bacon.statemachine.conditions.AttackCheckTransitionConditions.*;
import static com.bacon.statemachine.conditions.ClashTransitionConditions.CLASHED_OUT;
import static com.bacon.statemachine.conditions.RegularTransitionConditions.EMPTY;
import static com.bacon.statemachine.resolvers.internal.helper.EffectResolveMode.*;
import static java.util.Map.of;

public enum GameStates implements GameState {
    START {
        @Override
        public Map<StateTransitionCondition, GameState> nextStates() {
            return of(EMPTY, CHARACTERS_SELECTED);
        }

        @Override
        public StateTransitionCondition transition(GameInfoHolder holder) {
            return holder.resolversContainer.characterSelectionResolver.selectPlayers(holder);
        }
    },
    CHARACTERS_SELECTED {
        @Override
        public Map<StateTransitionCondition, GameState> nextStates() {
            return of(EMPTY, PLAYER_SETUP_FINISHED);
        }

        @Override
        public StateTransitionCondition transition(GameInfoHolder holder) {
            return holder.resolversContainer.discardResolver.selectDiscards(holder);
        }
    },
    PLAYER_SETUP_FINISHED {
        @Override
        public Map<StateTransitionCondition, GameState> nextStates() {
            return of(EMPTY, BEAT_START);
        }

        @Override
        public StateTransitionCondition transition(GameInfoHolder holder) {
            return EMPTY;
        }
    },
    BEAT_START {
        @Override
        public Map<StateTransitionCondition, GameState> nextStates() {
            return of(EMPTY, ACTIVE_ANTE);
        }

        @Override
        public StateTransitionCondition transition(GameInfoHolder holder) {
            return holder.resolversContainer.pairSelectionResolver.selectPairs(holder);
        }
    },
    ACTIVE_ANTE {
        @Override
        public Map<StateTransitionCondition, GameState> nextStates() {
            return of(EMPTY, REACTIVE_ANTE);
        }

        @Override
        public StateTransitionCondition transition(GameInfoHolder holder) {
            return holder.resolversContainer.anteResolver.ante(holder, true);
        }
    },
    REACTIVE_ANTE {
        @Override
        public Map<StateTransitionCondition, GameState> nextStates() {
            return of(EMPTY, REVEAL);
        }

        @Override
        public StateTransitionCondition transition(GameInfoHolder holder) {
            return holder.resolversContainer.anteResolver.ante(holder, false);
        }
    },
    REVEAL {
        @Override
        public Map<StateTransitionCondition, GameState> nextStates() {
            return of(EMPTY, PRIORITY_CHECK);
        }

        @Override
        public StateTransitionCondition transition(GameInfoHolder holder) {
            return holder.resolversContainer.effectsResolver.resolveEffects(holder, EffectTrigger.REVEAL, BOTH);
        }
    },
    PRIORITY_CHECK {
        @Override
        public Map<StateTransitionCondition, GameState> nextStates() {
            return of(
                    EMPTY, SOB,
                    CLASHED_OUT, RECYCLE
            );
        }

        @Override
        public StateTransitionCondition transition(GameInfoHolder holder) {
            return holder.resolversContainer.priorityResolver.resolvePriority(holder);
        }
    },
    SOB {
        @Override
        public Map<StateTransitionCondition, GameState> nextStates() {
            return of(EMPTY, ACTIVE_PLAYER_ATTACK_START);
        }

        @Override
        public StateTransitionCondition transition(GameInfoHolder holder) {
            return holder.resolversContainer.effectsResolver.resolveEffects(holder, EffectTrigger.SOB, ACTIVE_FIRST);
        }
    },
    ACTIVE_PLAYER_ATTACK_START {
        @Override
        public Map<StateTransitionCondition, GameState> nextStates() {
            return of(EMPTY, ACTIVE_PLAYER_BA);
        }

        @Override
        public StateTransitionCondition transition(GameInfoHolder holder) {
            return EMPTY;
        }
    },
    ACTIVE_PLAYER_BA {
        @Override
        public Map<StateTransitionCondition, GameState> nextStates() {
            return of(EMPTY, ACTIVE_PLAYER_RANGE_CHECK);
        }

        @Override
        public StateTransitionCondition transition(GameInfoHolder holder) {
            return holder.resolversContainer.effectsResolver.resolveEffects(holder, EffectTrigger.BA, ACTIVE);
        }
    },
    ACTIVE_PLAYER_RANGE_CHECK {
        @Override
        public Map<StateTransitionCondition, GameState> nextStates() {
            return of(
                    EMPTY, ACTIVE_PLAYER_OH,
                    MISS, ACTIVE_PLAYER_AA
            );
        }

        @Override
        public StateTransitionCondition transition(GameInfoHolder holder) {
            return holder.resolversContainer.rangeChecker.checkRange(holder, true);
        }
    },
    ACTIVE_PLAYER_OH {
        @Override
        public Map<StateTransitionCondition, GameState> nextStates() {
            return of(EMPTY, ACTIVE_PLAYER_DAMAGE);
        }

        @Override
        public StateTransitionCondition transition(GameInfoHolder holder) {
            return holder.resolversContainer.effectsResolver.resolveEffects(holder, EffectTrigger.OH, ACTIVE);
        }
    },
    ACTIVE_PLAYER_DAMAGE {
        @Override
        public Map<StateTransitionCondition, GameState> nextStates() {
            return of(
                    EMPTY, ACTIVE_PLAYER_OD,
                    NO_DAMAGE, ACTIVE_PLAYER_AA,
                    PLAYER_DEAD, GAME_END
            );
        }

        @Override
        public StateTransitionCondition transition(GameInfoHolder holder) {
            return holder.resolversContainer.damageResolver.resolveDamage(holder, true);
        }
    },
    ACTIVE_PLAYER_OD {
        @Override
        public Map<StateTransitionCondition, GameState> nextStates() {
            return of(EMPTY, ACTIVE_PLAYER_AA);
        }

        @Override
        public StateTransitionCondition transition(GameInfoHolder holder) {
            return holder.resolversContainer.effectsResolver.resolveEffects(holder, EffectTrigger.OD, ACTIVE);
        }
    },
    ACTIVE_PLAYER_AA {
        @Override
        public Map<StateTransitionCondition, GameState> nextStates() {
            return of(EMPTY, REACTIVE_STUN_CHECK);
        }

        @Override
        public StateTransitionCondition transition(GameInfoHolder holder) {
            return holder.resolversContainer.effectsResolver.resolveEffects(holder, EffectTrigger.AA, ACTIVE);
        }
    },
    REACTIVE_STUN_CHECK {
        @Override
        public Map<StateTransitionCondition, GameState> nextStates() {
            return of(
                    EMPTY, REACTIVE_PLAYER_ATTACK_START,
                    STUN, EOB
            );
        }

        @Override
        public StateTransitionCondition transition(GameInfoHolder holder) {
            return holder.resolversContainer.stunCheckResolver.checkStun(holder, false);
        }
    },
    REACTIVE_PLAYER_ATTACK_START {
        @Override
        public Map<StateTransitionCondition, GameState> nextStates() {
            return of(EMPTY, REACTIVE_PLAYER_BA);
        }

        @Override
        public StateTransitionCondition transition(GameInfoHolder holder) {
            return EMPTY;
        }
    },
    REACTIVE_PLAYER_BA {
        @Override
        public Map<StateTransitionCondition, GameState> nextStates() {
            return of(EMPTY, REACTIVE_PLAYER_RANGE_CHECK);
        }

        @Override
        public StateTransitionCondition transition(GameInfoHolder holder) {
            return holder.resolversContainer.effectsResolver.resolveEffects(holder, EffectTrigger.BA, REACTIVE);
        }
    },
    REACTIVE_PLAYER_RANGE_CHECK {
        @Override
        public Map<StateTransitionCondition, GameState> nextStates() {
            return of(
                    EMPTY, REACTIVE_PLAYER_OH,
                    MISS, REACTIVE_PLAYER_AA
            );
        }

        @Override
        public StateTransitionCondition transition(GameInfoHolder holder) {
            return holder.resolversContainer.rangeChecker.checkRange(holder, false);
        }
    },
    REACTIVE_PLAYER_OH {
        @Override
        public Map<StateTransitionCondition, GameState> nextStates() {
            return of(EMPTY, REACTIVE_PLAYER_DAMAGE);
        }

        @Override
        public StateTransitionCondition transition(GameInfoHolder holder) {
            return holder.resolversContainer.effectsResolver.resolveEffects(holder, EffectTrigger.OH, REACTIVE);
        }
    },
    REACTIVE_PLAYER_DAMAGE {
        @Override
        public Map<StateTransitionCondition, GameState> nextStates() {
            return of(
                    EMPTY, REACTIVE_PLAYER_OD,
                    NO_DAMAGE, REACTIVE_PLAYER_AA,
                    PLAYER_DEAD, GAME_END
            );
        }

        @Override
        public StateTransitionCondition transition(GameInfoHolder holder) {
            return holder.resolversContainer.damageResolver.resolveDamage(holder, false);
        }
    },
    REACTIVE_PLAYER_OD {
        @Override
        public Map<StateTransitionCondition, GameState> nextStates() {
            return of(EMPTY, REACTIVE_PLAYER_AA);
        }

        @Override
        public StateTransitionCondition transition(GameInfoHolder holder) {
            return holder.resolversContainer.effectsResolver.resolveEffects(holder, EffectTrigger.OD, REACTIVE);
        }
    },
    REACTIVE_PLAYER_AA {
        @Override
        public Map<StateTransitionCondition, GameState> nextStates() {
            return of(EMPTY, EOB);
        }

        @Override
        public StateTransitionCondition transition(GameInfoHolder holder) {
            return holder.resolversContainer.effectsResolver.resolveEffects(holder, EffectTrigger.AA, REACTIVE);
        }
    },
    EOB {
        @Override
        public Map<StateTransitionCondition, GameState> nextStates() {
            return of(EMPTY, RECYCLE);
        }

        @Override
        public StateTransitionCondition transition(GameInfoHolder holder) {
            return holder.resolversContainer.effectsResolver.resolveEffects(holder, EffectTrigger.EOB, ACTIVE_FIRST);
        }
    },
    RECYCLE {
        @Override
        public Map<StateTransitionCondition, GameState> nextStates() {
            return of(EMPTY, BEAT_START);
        }

        @Override
        public StateTransitionCondition transition(GameInfoHolder holder) {
            return holder.resolversContainer.recycler.recycle(holder);
        }
    },
    GAME_END {
        @Override
        public Map<StateTransitionCondition, GameState> nextStates() {
            return of();
        }

        @Override
        public StateTransitionCondition transition(GameInfoHolder holder) {
            return EMPTY;
        }
    }
}
