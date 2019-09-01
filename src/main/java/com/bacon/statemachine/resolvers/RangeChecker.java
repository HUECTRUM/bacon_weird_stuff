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
        int beatNum = holder.infoHelper.currentBeatNumber(holder);
        if (active) {
            holder.beatInfoHolder.activePlayerHit &= checkDist(
                    holder.beatInfoHolder.activePlayerPair.minRange(holder.beatInfoHolder.activePlayer, beatNum),
                    holder.beatInfoHolder.activePlayerPair.maxRange(holder.beatInfoHolder.activePlayer, beatNum),
                    holder
            );
        } else {
            holder.beatInfoHolder.reactivePlayerHit &= checkDist(
                    holder.beatInfoHolder.reactivePlayerPair.minRange(holder.beatInfoHolder.reactivePlayer, beatNum),
                    holder.beatInfoHolder.reactivePlayerPair.maxRange(holder.beatInfoHolder.reactivePlayer, beatNum),
                    holder
            );
        }

        boolean hit = active ? holder.beatInfoHolder.activePlayerHit : holder.beatInfoHolder.reactivePlayerHit;
        return hit ? EMPTY : MISS;
    }

    private boolean checkDist(Integer minRange, Integer maxRange, GameInfoHolder holder) {
        int playerDist = playerDist(holder);
        return minRange != null && maxRange != null
                && playerDist >= minRange && playerDist <= maxRange;
    }
}
