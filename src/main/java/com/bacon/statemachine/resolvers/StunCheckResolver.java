package com.bacon.statemachine.resolvers;

import com.bacon.holders.GameInfoHolder;
import com.bacon.statemachine.conditions.StateTransitionCondition;
import org.springframework.stereotype.Component;

import static com.bacon.statemachine.conditions.AttackCheckTransitionConditions.STUN;
import static com.bacon.statemachine.conditions.RegularTransitionConditions.EMPTY;

@Component
public class StunCheckResolver {
    public StateTransitionCondition checkStun(GameInfoHolder holder, boolean active) {
        boolean stunned = active
                ? holder.beatInfoHolder.activePlayerStunned : holder.beatInfoHolder.reactivePlayerStunned;
        return stunned ? STUN : EMPTY;
    }
}
