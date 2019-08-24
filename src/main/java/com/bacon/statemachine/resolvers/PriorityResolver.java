package com.bacon.statemachine.resolvers;

import com.bacon.attacks.AttackPairStatsCalculator;
import com.bacon.holders.GameInfoHolder;
import com.bacon.holders.beat.BeatInfoHolder;
import com.bacon.statemachine.conditions.StateTransitionCondition;
import com.bacon.statemachine.resolvers.internal.ClashResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import static com.bacon.statemachine.conditions.ClashTransitionConditions.CLASHED_OUT;
import static com.bacon.statemachine.conditions.RegularTransitionConditions.EMPTY;

@Component
public class PriorityResolver {
    @Autowired
    private AttackPairStatsCalculator statsCalculator;
    @Autowired
    private ClashResolver clashResolver;

    public StateTransitionCondition resolvePriority(GameInfoHolder holder) {
        BeatInfoHolder beatInfoHolder = holder.beatInfoHolder;
        boolean clashesResolved = resolveClashes(holder, beatInfoHolder);
        if (!clashesResolved) {
            return CLASHED_OUT;
        }

        BigDecimal firstPlayerPrio = statsCalculator.totalPriority(beatInfoHolder.firstPlayerPair);
        BigDecimal secondPlayerPrio = statsCalculator.totalPriority(beatInfoHolder.secondPlayerPair);

        boolean firstPlayerFaster = firstPlayerPrio.compareTo(secondPlayerPrio) >= 0;
        beatInfoHolder.activePlayer = firstPlayerFaster? holder.playerOne : holder.playerTwo;
        beatInfoHolder.reactivePlayer = firstPlayerFaster ? holder.playerTwo : holder.playerOne;

        beatInfoHolder.activePlayerPair =
                firstPlayerFaster ? beatInfoHolder.firstPlayerPair : beatInfoHolder.secondPlayerPair;
        beatInfoHolder.reactivePlayerPair =
                firstPlayerFaster ? beatInfoHolder.secondPlayerPair : beatInfoHolder.firstPlayerPair;
        return EMPTY;
    }

    private boolean resolveClashes(GameInfoHolder holder, BeatInfoHolder beatInfoHolder) {
        BigDecimal firstPlayerPrio = statsCalculator.totalPriority(beatInfoHolder.firstPlayerPair);
        BigDecimal secondPlayerPrio = statsCalculator.totalPriority(beatInfoHolder.secondPlayerPair);

        while (firstPlayerPrio.compareTo(secondPlayerPrio) == 0) {
            boolean clashResolved = clashResolver.resolveClash(holder);
            if (!clashResolved) {
                return false;
            }

            firstPlayerPrio = statsCalculator.totalPriority(beatInfoHolder.firstPlayerPair);
            secondPlayerPrio = statsCalculator.totalPriority(beatInfoHolder.secondPlayerPair);
        }
        return true;
    }
}