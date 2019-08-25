package com.bacon.statemachine.resolvers;

import com.bacon.holders.GameInfoHolder;
import com.bacon.statemachine.conditions.StateTransitionCondition;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.bacon.statemachine.conditions.AttackCheckTransitionConditions.MISS;
import static com.bacon.statemachine.conditions.RegularTransitionConditions.EMPTY;
import static java.lang.Math.abs;
import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.range;

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

    private int playerDist(GameInfoHolder holder) {
        List<Integer> playersPoints = range(0, holder.field.spaces.size())
                .filter(ind -> holder.field.spaces.get(ind) != null)
                .boxed()
                .collect(toList());

        return abs(playersPoints.get(0) - playersPoints.get(1));
    }
}
