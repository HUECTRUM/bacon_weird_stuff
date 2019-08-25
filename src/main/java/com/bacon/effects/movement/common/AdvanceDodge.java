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
public class AdvanceDodge implements CardEffect {
    public int spaces;

    @Override
    public void apply(Player player, GameInfoHolder gameInfoHolder) {
        Direction advanceDirection = advanceDirection(gameInfoHolder, player);

        move(
                gameInfoHolder,
                player,
                advanceDirection,
                spaces >= playerDist(gameInfoHolder) ? spaces + 1 : spaces
        );

        if (advanceDirection != advanceDirection(gameInfoHolder, player)) {
            dodgeProc(player, gameInfoHolder);
        }
    }

    private void dodgeProc(Player player, GameInfoHolder gameInfoHolder) {
        if (!player.equals(gameInfoHolder.beatInfoHolder.reactivePlayer)) {
            gameInfoHolder.beatInfoHolder.reactivePlayerHit = false;
        } else {
            gameInfoHolder.beatInfoHolder.activePlayerHit = false;
        }
    }
}