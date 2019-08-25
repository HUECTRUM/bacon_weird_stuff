package com.bacon.helper;

import com.bacon.calculation.MovementCalculator;
import com.bacon.effects.movement.common.Direction;
import com.bacon.holders.GameInfoHolder;
import com.bacon.player.Player;
import lombok.extern.slf4j.Slf4j;

import static com.bacon.effects.movement.common.Direction.LEFT;

@Slf4j
//todo: singleton
public class MovementHelper {
    private MovementCalculator movementCalculator = new MovementCalculator();

    public void move(GameInfoHolder holder, Player player, Direction direction, int spaces) {
        int index = movementCalculator.findPlayerIndex(holder, player);
        int endIndex = (direction == LEFT) ? index - spaces : index + spaces;

        if (endIndex < 0 || endIndex > 6) {
            log.info("Failed to move - out of bounds");
            return;
        }

        holder.field.spaces.set(index, null);
        holder.field.spaces.set(endIndex, player.playerId);
    }
}
