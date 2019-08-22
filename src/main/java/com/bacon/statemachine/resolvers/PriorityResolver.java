package com.bacon.statemachine.resolvers;

import com.bacon.attacks.AttackPairStatsCalculator;
import com.bacon.holders.GameInfoHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class PriorityResolver {
    @Autowired
    AttackPairStatsCalculator statsCalculator;

    public void resolvePriority(GameInfoHolder holder) {
        BigDecimal firstPlayerPrio = statsCalculator.totalPriority(holder.beatInfoHolder.firstPlayerPair);
        BigDecimal secondPlayerPrio = statsCalculator.totalPriority(holder.beatInfoHolder.secondPlayerPair);

        boolean firstPlayerFaster = firstPlayerPrio.compareTo(secondPlayerPrio) >= 0;
        holder.beatInfoHolder.activePlayer = firstPlayerFaster? holder.playerOne : holder.playerTwo;
        holder.beatInfoHolder.reactivePlayer = firstPlayerFaster ? holder.playerTwo : holder.playerOne;

        holder.beatInfoHolder.activePlayerPair =
                firstPlayerFaster ? holder.beatInfoHolder.firstPlayerPair : holder.beatInfoHolder.secondPlayerPair;
        holder.beatInfoHolder.reactivePlayerPair =
                firstPlayerFaster ? holder.beatInfoHolder.secondPlayerPair : holder.beatInfoHolder.firstPlayerPair;
    }
}
