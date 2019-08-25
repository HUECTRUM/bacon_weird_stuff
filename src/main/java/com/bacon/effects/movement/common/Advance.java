package com.bacon.effects.movement.common;

import com.bacon.calculation.MovementCalculator;
import com.bacon.gameobjects.cards.CardEffect;
import com.bacon.helper.MovementHelper;
import com.bacon.holders.GameInfoHolder;
import com.bacon.player.Player;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import static com.bacon.utils.FieldUtils.playerDist;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Advance implements CardEffect {
    private MovementCalculator movementCalculator = new MovementCalculator();
    private MovementHelper movementHelper = new MovementHelper();

    public int spaces;

    public Advance(int spaces) {
        this.spaces = spaces;
    }

    @Override
    public void apply(Player player, GameInfoHolder gameInfoHolder) {
        movementHelper.move(
                gameInfoHolder,
                player,
                movementCalculator.advanceDirection(gameInfoHolder, player),
                spaces >= playerDist(gameInfoHolder) ? spaces + 1 : spaces
        );
    }
}