package com.bacon.effects.movement.common;

import com.bacon.calculation.MovementCalculator;
import com.bacon.gameobjects.cards.CardEffect;
import com.bacon.helper.MovementHelper;
import com.bacon.holders.GameInfoHolder;
import com.bacon.player.Player;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@AllArgsConstructor
public class Retreat implements CardEffect {
    @Autowired
    public MovementCalculator movementCalculator;
    @Autowired
    public MovementHelper movementHelper;

    public int spaces;

    @Override
    public void apply(Player player, GameInfoHolder gameInfoHolder) {
        movementHelper.move(
                gameInfoHolder,
                player,
                movementCalculator.retreatDirection(gameInfoHolder, player),
                spaces
        );
    }
}
