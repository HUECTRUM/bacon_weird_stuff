package com.bacon.gamefiles.statemachine.resolvers;

import com.bacon.gamefiles.holders.GameInfoHolder;
import com.bacon.gamefiles.player.Player;
import com.bacon.gamefiles.statemachine.conditions.StateTransitionCondition;
import org.springframework.stereotype.Component;

import static com.bacon.gamefiles.statemachine.conditions.AttackCheckTransitionConditions.STUN;
import static com.bacon.gamefiles.statemachine.conditions.RegularTransitionConditions.EMPTY;

@Component
public class StunCheckResolver {
    public StateTransitionCondition checkStun(GameInfoHolder holder, boolean active) {
        Player player = active ? holder.beatInfoHolder.activePlayer : holder.beatInfoHolder.reactivePlayer;
        return player.beatHolder.stunned ? STUN : EMPTY;
    }
}
