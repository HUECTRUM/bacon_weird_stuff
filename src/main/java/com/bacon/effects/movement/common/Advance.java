package com.bacon.effects.movement.common;

import com.bacon.gameobjects.cards.CardEffect;
import com.bacon.holders.GameInfoHolder;
import com.bacon.player.Player;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import static com.bacon.utils.FieldUtils.playerDist;
import static com.bacon.utils.calculation.MovementCalculator.advanceDirection;
import static com.bacon.utils.helper.MovementHelper.move;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Advance implements CardEffect {
    public int spaces;

    @Override
    public void apply(Player player, GameInfoHolder gameInfoHolder) {
        move(
                gameInfoHolder,
                player,
                advanceDirection(gameInfoHolder, player),
                spaces >= playerDist(gameInfoHolder) ? spaces + 1 : spaces
        );
    }
}