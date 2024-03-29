package com.bacon.gamefiles.statemachine;

import com.bacon.gamefiles.events.EventType;
import com.bacon.gamefiles.gameobjects.triggers.EffectTrigger;
import com.bacon.gamefiles.holders.GameInfoHolder;
import com.bacon.gamefiles.statemachine.conditions.StateTransitionCondition;
import com.bacon.gamefiles.statemachine.states.GameState;
import lombok.AllArgsConstructor;

import java.util.Map;

import static com.bacon.gamefiles.statemachine.conditions.AttackCheckTransitionConditions.*;
import static com.bacon.gamefiles.statemachine.conditions.ClashTransitionConditions.CLASHED_OUT;
import static com.bacon.gamefiles.statemachine.conditions.RegularTransitionConditions.EMPTY;
import static com.bacon.gamefiles.statemachine.resolvers.internal.helper.EffectResolveMode.*;
import static java.util.Map.of;

@AllArgsConstructor
public enum GameStates implements GameState {
    START(EventType.CHARACTER_SELECT) {
        @Override
        public Map<StateTransitionCondition, GameStates> nextStates() {
            return of(EMPTY, CHARACTERS_SELECTED);
        }

        @Override
        public StateTransitionCondition transitionInternal(GameInfoHolder holder) {
            return holder.resolversContainer.characterSelectionResolver.selectPlayers(holder);
        }
    },
    CHARACTERS_SELECTED(EventType.DISCARD_SELECT) {
        @Override
        public Map<StateTransitionCondition, GameStates> nextStates() {
            return of(EMPTY, PLAYER_SETUP_FINISHED);
        }

        @Override
        public StateTransitionCondition transitionInternal(GameInfoHolder holder) {
            return holder.resolversContainer.discardResolver.selectDiscards(holder);
        }
    },
    PLAYER_SETUP_FINISHED(null) {
        @Override
        public Map<StateTransitionCondition, GameStates> nextStates() {
            return of(EMPTY, BEAT_START);
        }

        @Override
        public StateTransitionCondition transitionInternal(GameInfoHolder holder) {
            return EMPTY;
        }
    },
    BEAT_START(EventType.PAIR_SELECT) {
        @Override
        public Map<StateTransitionCondition, GameStates> nextStates() {
            return of(EMPTY, ACTIVE_ANTE);
        }

        @Override
        public StateTransitionCondition transitionInternal(GameInfoHolder holder) {
            return holder.resolversContainer.pairSelectionResolver.selectPairs(holder);
        }
    },
    ACTIVE_ANTE(null) {
        @Override
        public Map<StateTransitionCondition, GameStates> nextStates() {
            return of(EMPTY, REACTIVE_ANTE);
        }

        @Override
        public StateTransitionCondition transitionInternal(GameInfoHolder holder) {
            return holder.resolversContainer.anteResolver.ante(holder, true);
        }
    },
    REACTIVE_ANTE(null) {
        @Override
        public Map<StateTransitionCondition, GameStates> nextStates() {
            return of(EMPTY, PRE_REVEAL);
        }

        @Override
        public StateTransitionCondition transitionInternal(GameInfoHolder holder) {
            return holder.resolversContainer.anteResolver.ante(holder, false);
        }
    },
    PRE_REVEAL(null) {
        @Override
        public Map<StateTransitionCondition, GameStates> nextStates() {
            return of(EMPTY, REVEAL);
        }

        @Override
        public StateTransitionCondition transitionInternal(GameInfoHolder holder) {
            return holder.resolversContainer.preRevealResolver.resolve(holder);
        }
    },
    REVEAL(EventType.REVEAL) {
        @Override
        public Map<StateTransitionCondition, GameStates> nextStates() {
            return of(EMPTY, PRIORITY_CHECK);
        }

        @Override
        public StateTransitionCondition transitionInternal(GameInfoHolder holder) {
            return holder.resolversContainer.effectsResolver.resolveEffects(holder, EffectTrigger.REVEAL, BOTH);
        }
    },
    PRIORITY_CHECK(null) {
        @Override
        public Map<StateTransitionCondition, GameStates> nextStates() {
            return of(
                    EMPTY, SOB,
                    CLASHED_OUT, RECYCLE
            );
        }

        @Override
        public StateTransitionCondition transitionInternal(GameInfoHolder holder) {
            return holder.resolversContainer.priorityResolver.resolvePriority(holder);
        }
    },
    SOB(EventType.SOB) {
        @Override
        public Map<StateTransitionCondition, GameStates> nextStates() {
            return of(EMPTY, ACTIVE_PLAYER_ATTACK_START);
        }

        @Override
        public StateTransitionCondition transitionInternal(GameInfoHolder holder) {
            return holder.resolversContainer.effectsResolver.resolveEffects(holder, EffectTrigger.SOB, ACTIVE_FIRST);
        }
    },
    ACTIVE_PLAYER_ATTACK_START(null) {
        @Override
        public Map<StateTransitionCondition, GameStates> nextStates() {
            return of(EMPTY, ACTIVE_PLAYER_BA);
        }

        @Override
        public StateTransitionCondition transitionInternal(GameInfoHolder holder) {
            return EMPTY;
        }
    },
    ACTIVE_PLAYER_BA(EventType.BA) {
        @Override
        public Map<StateTransitionCondition, GameStates> nextStates() {
            return of(EMPTY, ACTIVE_PLAYER_BEFORE_RANGE_CHECK);
        }

        @Override
        public StateTransitionCondition transitionInternal(GameInfoHolder holder) {
            return holder.resolversContainer.effectsResolver.resolveEffects(holder, EffectTrigger.BA, ACTIVE);
        }
    },
    ACTIVE_PLAYER_BEFORE_RANGE_CHECK(null) {
        @Override
        public Map<StateTransitionCondition, GameStates> nextStates() {
            return of(EMPTY, ACTIVE_PLAYER_RANGE_CHECK);
        }

        @Override
        public StateTransitionCondition transitionInternal(GameInfoHolder holder) {
            return holder.resolversContainer.effectsResolver.resolveEffects(holder, EffectTrigger.BEFORE_RANGE_CHECK, ACTIVE);
        }
    },
    ACTIVE_PLAYER_RANGE_CHECK(null) {
        @Override
        public Map<StateTransitionCondition, GameStates> nextStates() {
            return of(
                    EMPTY, ACTIVE_PLAYER_OH,
                    MISS, ACTIVE_PLAYER_AA
            );
        }

        @Override
        public StateTransitionCondition transitionInternal(GameInfoHolder holder) {
            return holder.resolversContainer.rangeChecker.checkRange(holder, true);
        }
    },
    ACTIVE_PLAYER_OH(EventType.OH) {
        @Override
        public Map<StateTransitionCondition, GameStates> nextStates() {
            return of(EMPTY, ACITIVE_PLAYER_BEFORE_DAMAGE);
        }

        @Override
        public StateTransitionCondition transitionInternal(GameInfoHolder holder) {
            return holder.resolversContainer.effectsResolver.resolveEffects(holder, EffectTrigger.OH, ACTIVE);
        }
    },
    ACITIVE_PLAYER_BEFORE_DAMAGE(null) {
        @Override
        public Map<StateTransitionCondition, GameStates> nextStates() {
            return of(EMPTY, ACTIVE_PLAYER_DAMAGE);
        }

        @Override
        public StateTransitionCondition transitionInternal(GameInfoHolder holder) {
            return holder.resolversContainer.effectsResolver.resolveEffects(holder, EffectTrigger.BEFORE_DAMAGE, ACTIVE);
        }
    },
    ACTIVE_PLAYER_DAMAGE(null) {
        @Override
        public Map<StateTransitionCondition, GameStates> nextStates() {
            return of(
                    EMPTY, ACTIVE_PLAYER_OD,
                    NO_DAMAGE, ACTIVE_PLAYER_AA,
                    PLAYER_DEAD, GAME_END
            );
        }

        @Override
        public StateTransitionCondition transitionInternal(GameInfoHolder holder) {
            return holder.resolversContainer.damageResolver.resolveDamage(holder, true);
        }
    },
    ACTIVE_PLAYER_OD(EventType.OD) {
        @Override
        public Map<StateTransitionCondition, GameStates> nextStates() {
            return of(EMPTY, ACTIVE_PLAYER_AA);
        }

        @Override
        public StateTransitionCondition transitionInternal(GameInfoHolder holder) {
            return holder.resolversContainer.effectsResolver.resolveEffects(holder, EffectTrigger.OD, ACTIVE);
        }
    },
    ACTIVE_PLAYER_AA(EventType.AA) {
        @Override
        public Map<StateTransitionCondition, GameStates> nextStates() {
            return of(EMPTY, REACTIVE_STUN_CHECK);
        }

        @Override
        public StateTransitionCondition transitionInternal(GameInfoHolder holder) {
            return holder.resolversContainer.effectsResolver.resolveEffects(holder, EffectTrigger.AA, ACTIVE);
        }
    },
    REACTIVE_STUN_CHECK(null) {
        @Override
        public Map<StateTransitionCondition, GameStates> nextStates() {
            return of(
                    EMPTY, REACTIVE_PLAYER_ATTACK_START,
                    STUN, EOB
            );
        }

        @Override
        public StateTransitionCondition transitionInternal(GameInfoHolder holder) {
            return holder.resolversContainer.stunCheckResolver.checkStun(holder, false);
        }
    },
    REACTIVE_PLAYER_ATTACK_START(null) {
        @Override
        public Map<StateTransitionCondition, GameStates> nextStates() {
            return of(EMPTY, REACTIVE_PLAYER_BA);
        }

        @Override
        public StateTransitionCondition transitionInternal(GameInfoHolder holder) {
            return EMPTY;
        }
    },
    REACTIVE_PLAYER_BA(EventType.BA) {
        @Override
        public Map<StateTransitionCondition, GameStates> nextStates() {
            return of(EMPTY, REACTIVE_PLAYER_BEFORE_RANGE_CHECK);
        }

        @Override
        public StateTransitionCondition transitionInternal(GameInfoHolder holder) {
            return holder.resolversContainer.effectsResolver.resolveEffects(holder, EffectTrigger.BA, REACTIVE);
        }
    },
    REACTIVE_PLAYER_BEFORE_RANGE_CHECK(null) {
        @Override
        public Map<StateTransitionCondition, GameStates> nextStates() {
            return of(EMPTY, REACTIVE_PLAYER_RANGE_CHECK);
        }

        @Override
        public StateTransitionCondition transitionInternal(GameInfoHolder holder) {
            return holder.resolversContainer.effectsResolver.resolveEffects(holder, EffectTrigger.BEFORE_RANGE_CHECK, REACTIVE);
        }
    },
    REACTIVE_PLAYER_RANGE_CHECK(null) {
        @Override
        public Map<StateTransitionCondition, GameStates> nextStates() {
            return of(
                    EMPTY, REACTIVE_PLAYER_OH,
                    MISS, REACTIVE_PLAYER_AA
            );
        }

        @Override
        public StateTransitionCondition transitionInternal(GameInfoHolder holder) {
            return holder.resolversContainer.rangeChecker.checkRange(holder, false);
        }
    },
    REACTIVE_PLAYER_OH(EventType.OH) {
        @Override
        public Map<StateTransitionCondition, GameStates> nextStates() {
            return of(EMPTY, REACTIVE_PLAYER_BEFORE_DAMAGE);
        }

        @Override
        public StateTransitionCondition transitionInternal(GameInfoHolder holder) {
            return holder.resolversContainer.effectsResolver.resolveEffects(holder, EffectTrigger.OH, REACTIVE);
        }
    },
    REACTIVE_PLAYER_BEFORE_DAMAGE(null) {
        @Override
        public Map<StateTransitionCondition, GameStates> nextStates() {
            return of(EMPTY, REACTIVE_PLAYER_DAMAGE);
        }

        @Override
        public StateTransitionCondition transitionInternal(GameInfoHolder holder) {
            return holder.resolversContainer.effectsResolver.resolveEffects(holder, EffectTrigger.BEFORE_DAMAGE, REACTIVE);
        }
    },
    REACTIVE_PLAYER_DAMAGE(null) {
        @Override
        public Map<StateTransitionCondition, GameStates> nextStates() {
            return of(
                    EMPTY, REACTIVE_PLAYER_OD,
                    NO_DAMAGE, REACTIVE_PLAYER_AA,
                    PLAYER_DEAD, GAME_END
            );
        }

        @Override
        public StateTransitionCondition transitionInternal(GameInfoHolder holder) {
            return holder.resolversContainer.damageResolver.resolveDamage(holder, false);
        }
    },
    REACTIVE_PLAYER_OD(EventType.OD) {
        @Override
        public Map<StateTransitionCondition, GameStates> nextStates() {
            return of(EMPTY, REACTIVE_PLAYER_AA);
        }

        @Override
        public StateTransitionCondition transitionInternal(GameInfoHolder holder) {
            return holder.resolversContainer.effectsResolver.resolveEffects(holder, EffectTrigger.OD, REACTIVE);
        }
    },
    REACTIVE_PLAYER_AA(null) {
        @Override
        public Map<StateTransitionCondition, GameStates> nextStates() {
            return of(EMPTY, EOB);
        }

        @Override
        public StateTransitionCondition transitionInternal(GameInfoHolder holder) {
            return holder.resolversContainer.effectsResolver.resolveEffects(holder, EffectTrigger.AA, REACTIVE);
        }
    },
    EOB(EventType.EOB) {
        @Override
        public Map<StateTransitionCondition, GameStates> nextStates() {
            return of(EMPTY, RECYCLE);
        }

        @Override
        public StateTransitionCondition transitionInternal(GameInfoHolder holder) {
            return holder.resolversContainer.effectsResolver.resolveEffects(holder, EffectTrigger.EOB, ACTIVE_FIRST);
        }
    },
    RECYCLE(EventType.RECYCLE) {
        @Override
        public Map<StateTransitionCondition, GameStates> nextStates() {
            return of(EMPTY, BEAT_START);
        }

        @Override
        public StateTransitionCondition transitionInternal(GameInfoHolder holder) {
            return holder.resolversContainer.recycler.recycle(holder);
        }
    },
    GAME_END(null) {
        @Override
        public Map<StateTransitionCondition, GameStates> nextStates() {
            return of();
        }

        @Override
        public StateTransitionCondition transitionInternal(GameInfoHolder holder) {
            return EMPTY;
        }
    };

    public EventType eventType;
}

