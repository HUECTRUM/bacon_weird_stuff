package com.bacon.statemachine.resolvers;

import com.bacon.attacks.AttackPairStatsCalculator;
import com.bacon.holders.GameInfoHolder;
import com.bacon.statemachine.conditions.StateTransitionCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.bacon.statemachine.conditions.RegularTransitionConditions.EMPTY;
import static java.lang.Math.abs;
import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.range;

@Component
public class RangeChecker {
    @Autowired
    private AttackPairStatsCalculator pairStatsCalculator;

    public StateTransitionCondition checkRange(GameInfoHolder holder, boolean active) {
        if (active) {
            holder.beatInfoHolder.activePlayerHit &= checkDist(
                    pairStatsCalculator.minRange(holder.beatInfoHolder.activePlayerPair),
                    pairStatsCalculator.maxRange(holder.beatInfoHolder.activePlayerPair),
                    holder
            );
        } else {
            holder.beatInfoHolder.reactivePlayerHit &= checkDist(
                    pairStatsCalculator.minRange(holder.beatInfoHolder.reactivePlayerPair),
                    pairStatsCalculator.maxRange(holder.beatInfoHolder.reactivePlayerPair),
                    holder
            );
        }
        return EMPTY;
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
