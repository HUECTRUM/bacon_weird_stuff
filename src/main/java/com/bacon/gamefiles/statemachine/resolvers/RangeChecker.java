package com.bacon.gamefiles.statemachine.resolvers;

import com.bacon.gamefiles.holders.GameInfoHolder;
import com.bacon.gamefiles.player.Player;
import com.bacon.gamefiles.statemachine.conditions.StateTransitionCondition;
import org.springframework.stereotype.Component;

import static com.bacon.gamefiles.statemachine.conditions.AttackCheckTransitionConditions.MISS;
import static com.bacon.gamefiles.statemachine.conditions.RegularTransitionConditions.EMPTY;
import static com.bacon.gamefiles.utils.FieldUtils.playerDist;

@Component
public class RangeChecker {
    public StateTransitionCondition checkRange(GameInfoHolder holder, boolean active) {
        int beatNum = holder.infoHelper.currentBeatNumber(holder);
        Player player = active ? holder.beatInfoHolder.activePlayer : holder.beatInfoHolder.reactivePlayer;

        player.beatHolder.attackHit &= checkDist(
                player.beatHolder.currentBeatPair.minRange(player, beatNum),
                player.beatHolder.currentBeatPair.maxRange(player, beatNum),
                holder
        );

        return player.beatHolder.attackHit ? EMPTY : MISS;
    }

    private boolean checkDist(Integer minRange, Integer maxRange, GameInfoHolder holder) {
        int playerDist = playerDist(holder);
        return minRange != null && maxRange != null
                && playerDist >= minRange && playerDist <= maxRange;
    }
}
