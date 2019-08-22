package com.bacon.statemachine.resolvers;

import com.bacon.attacks.AttackPairStatsCalculator;
import com.bacon.holders.GameInfoHolder;
import com.bacon.statemachine.conditions.StateTransitionCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import static com.bacon.statemachine.conditions.RegularTransitionConditions.EMPTY;

@Component
public class PriorityResolver {
    @Autowired
    AttackPairStatsCalculator statsCalculator;

    public StateTransitionCondition resolvePriority(GameInfoHolder holder) {
        BigDecimal firstPlayerPrio = statsCalculator.totalPriority(holder.beatInfoHolder.firstPlayerPair);
        BigDecimal secondPlayerPrio = statsCalculator.totalPriority(holder.beatInfoHolder.secondPlayerPair);

        boolean firstPlayerFaster = firstPlayerPrio.compareTo(secondPlayerPrio) >= 0;
        holder.beatInfoHolder.activePlayer = firstPlayerFaster? holder.playerOne : holder.playerTwo;
        holder.beatInfoHolder.reactivePlayer = firstPlayerFaster ? holder.playerTwo : holder.playerOne;

        holder.beatInfoHolder.activePlayerPair =
                firstPlayerFaster ? holder.beatInfoHolder.firstPlayerPair : holder.beatInfoHolder.secondPlayerPair;
        holder.beatInfoHolder.reactivePlayerPair =
                firstPlayerFaster ? holder.beatInfoHolder.secondPlayerPair : holder.beatInfoHolder.firstPlayerPair;
        return EMPTY;
    }
}
