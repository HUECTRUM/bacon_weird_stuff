package com.bacon.effects.movement.common;

import com.bacon.calculation.MovementCalculator;
import com.bacon.gameobjects.cards.CardEffect;
import com.bacon.helper.MovementHelper;
import com.bacon.holders.GameInfoHolder;
import com.bacon.player.Player;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Retreat implements CardEffect {
    private MovementCalculator movementCalculator = new MovementCalculator();
    private MovementHelper movementHelper = new MovementHelper();

    public int spaces;

    public Retreat(int spaces) {
        this.spaces = spaces;
    }

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
