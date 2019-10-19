package com.bacon.gamefiles.utils.helper;

import com.bacon.gamefiles.effects.movement.common.Direction;
import com.bacon.gamefiles.events.EventEmitter;
import com.bacon.gamefiles.holders.GameInfoHolder;
import com.bacon.gamefiles.player.Player;
import com.bacon.gamefiles.utils.calculation.MovementCalculator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.bacon.gamefiles.effects.movement.common.Direction.LEFT;
import static com.bacon.gamefiles.events.EventType.BOARD_CHANGED;
import static com.bacon.gamefiles.events.GameEvent.event;
import static java.util.List.of;

@Slf4j
@Component
public class MovementHelper {
    @Autowired
    private MovementCalculator movementCalculator;
    @Autowired
    private EventEmitter emitter;

    public void move(GameInfoHolder holder, Player player, Direction direction, int spaces) {
        int index = movementCalculator.findPlayerIndex(holder, player);
        int endIndex = (direction == LEFT) ? index - spaces : index + spaces;

        if (endIndex < 0 || endIndex > 6) {
            log.info("Failed to move - out of bounds");
            return;
        }

        holder.field.spaces.set(index, null);
        holder.field.spaces.set(endIndex, player.playerId);

        emitter.emit(event(BOARD_CHANGED, of(holder.field), holder.gameId));
    }
}
