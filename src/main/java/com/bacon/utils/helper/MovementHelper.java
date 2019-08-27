package com.bacon.utils.helper;

import com.bacon.effects.movement.common.Direction;
import com.bacon.holders.GameInfoHolder;
import com.bacon.player.Player;
import lombok.extern.slf4j.Slf4j;

import static com.bacon.effects.movement.common.Direction.LEFT;
import static com.bacon.utils.calculation.MovementCalculator.findPlayerIndex;

@Slf4j
public class MovementHelper {
    public static void move(GameInfoHolder holder, Player player, Direction direction, int spaces) {
        int index = findPlayerIndex(holder, player);
        int endIndex = (direction == LEFT) ? index - spaces : index + spaces;

        if (endIndex < 0 || endIndex > 6) {
            log.info("Failed to move - out of bounds");
            return;
        }

        holder.field.spaces.set(index, null);
        holder.field.spaces.set(endIndex, player.playerId);
    }
}
