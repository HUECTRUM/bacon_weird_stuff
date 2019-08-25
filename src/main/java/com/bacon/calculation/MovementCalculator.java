package com.bacon.calculation;

import com.bacon.effects.movement.common.Direction;
import com.bacon.holders.GameInfoHolder;
import com.bacon.player.Player;

import java.util.function.IntPredicate;
import java.util.stream.IntStream;

import static com.bacon.effects.movement.common.Direction.LEFT;
import static com.bacon.effects.movement.common.Direction.RIGHT;

//todo: singleton
public class MovementCalculator {
    public int findPlayerIndex(GameInfoHolder holder, Player player) {
        return findIndex(
                holder,
                ind -> holder.field.spaces.get(ind) != null && holder.field.spaces.get(ind).equals(player.playerId)
        );
    }

    public int findOpponentIndex(GameInfoHolder holder, Player player) {
        return findIndex(
                holder,
                ind -> holder.field.spaces.get(ind) != null && !holder.field.spaces.get(ind).equals(player.playerId)
        );
    }

    public Direction advanceDirection(GameInfoHolder holder, Player player) {
        return findPlayerIndex(holder, player) < findOpponentIndex(holder, player) ? RIGHT : LEFT;
    }

    public Direction retreatDirection(GameInfoHolder holder, Player player) {
        return advanceDirection(holder, player) == LEFT ? RIGHT : LEFT;
    }

    private int findIndex(GameInfoHolder holder, IntPredicate filterFunc) {
        return IntStream.range(0, holder.field.spaces.size())
                .filter(filterFunc)
                .boxed()
                .findFirst()
                .orElse(-1);
    }
}