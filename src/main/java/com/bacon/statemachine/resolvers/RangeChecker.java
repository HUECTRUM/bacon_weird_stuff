package com.bacon.statemachine.resolvers;

import com.bacon.holders.GameInfoHolder;
import com.bacon.statemachine.conditions.StateTransitionCondition;
import org.springframework.stereotype.Component;

import static com.bacon.statemachine.conditions.AttackCheckTransitionConditions.MISS;
import static com.bacon.statemachine.conditions.RegularTransitionConditions.EMPTY;
import static com.bacon.utils.FieldUtils.playerDist;

@Component
public class RangeChecker {
    public StateTransitionCondition checkRange(GameInfoHolder holder, boolean active) {
        if (active) {
            holder.beatInfoHolder.activePlayerHit &= checkDist(
                    holder.beatInfoHolder.activePlayerPair.minRange(),
                    holder.beatInfoHolder.activePlayerPair.maxRange(),
                    holder
            );
        } else {
            holder.beatInfoHolder.reactivePlayerHit &= checkDist(
                    holder.beatInfoHolder.reactivePlayerPair.minRange(),
                    holder.beatInfoHolder.reactivePlayerPair.maxRange(),
                    holder
            );
        }

        boolean hit = active ? holder.beatInfoHolder.activePlayerHit : holder.beatInfoHolder.reactivePlayerHit;
        return hit ? EMPTY : MISS;
    }

    private boolean checkDist(int minRange, int maxRange, GameInfoHolder holder) {
        int playerDist = playerDist(holder);
        return playerDist >= minRange && playerDist <= maxRange;
    }
}
