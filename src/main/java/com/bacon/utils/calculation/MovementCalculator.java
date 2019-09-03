package com.bacon.utils.calculation;

import com.bacon.effects.movement.common.Direction;
import com.bacon.holders.GameInfoHolder;
import com.bacon.player.Player;

import java.util.function.IntPredicate;
import java.util.stream.IntStream;

import static com.bacon.effects.movement.common.Direction.LEFT;
import static com.bacon.effects.movement.common.Direction.RIGHT;

public class MovementCalculator {
    public static int findPlayerIndex(GameInfoHolder holder, Player player) {
        return findIndex(
                holder,
                ind -> holder.field.spaces.get(ind) != null && holder.field.spaces.get(ind).equals(player.playerId)
        );
    }

    public static int findOpponentIndex(GameInfoHolder holder, Player player) {
        return findIndex(
                holder,
                ind -> holder.field.spaces.get(ind) != null && !holder.field.spaces.get(ind).equals(player.playerId)
        );
    }

    public static Direction advanceDirection(GameInfoHolder holder, Player player) {
        return findPlayerIndex(holder, player) < findOpponentIndex(holder, player) ? RIGHT : LEFT;
    }

    public static Direction retreatDirection(GameInfoHolder holder, Player player) {
        return advanceDirection(holder, player) == LEFT ? RIGHT : LEFT;
    }

    public static Direction pushDirection(GameInfoHolder holder, Player player) {
        return advanceDirection(holder, player);
    }

    public static Direction pullDirection(GameInfoHolder holder, Player player) {
        return retreatDirection(holder, player);
    }

    private static int findIndex(GameInfoHolder holder, IntPredicate filterFunc) {
        return IntStream.range(0, holder.field.spaces.size())
                .filter(filterFunc)
                .boxed()
                .findFirst()
                .orElse(-1);
    }

    //choices selection
    //todo: code duplication
    public static int maxAvailableAdvance(GameInfoHolder holder, Player player) {
        int index = findPlayerIndex(holder, player);
        Direction advanceDirection = advanceDirection(holder, player);
        return advanceDirection == LEFT ? index - 1 : 5 - index; //one space is occupied by the opponent
    }

    public static int maxAvailableRetreat(GameInfoHolder holder, Player player) {
        int index = findPlayerIndex(holder, player);
        Direction retreatDirection = retreatDirection(holder, player);
        return retreatDirection == LEFT ? index : 6 - index;
    }

    public static int maxAvailablePush(GameInfoHolder holder, Player player) {
        int index = findOpponentIndex(holder, player);
        Direction direction = pushDirection(holder, player);
        return direction == LEFT ? index : 6 - index;
    }

    public static int maxAvailablePull(GameInfoHolder holder, Player player) {
        int index = findOpponentIndex(holder, player);
        Direction direction = pullDirection(holder, player);
        return direction == LEFT ? index - 1 : 5 - index;
    }
}
