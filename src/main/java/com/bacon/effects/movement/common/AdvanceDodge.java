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
public class AdvanceDodge implements CardEffect {
    private MovementCalculator movementCalculator = new MovementCalculator();
    private MovementHelper movementHelper = new MovementHelper();

    public int spaces;

    public AdvanceDodge(int spaces) {
        this.spaces = spaces;
    }

    @Override
    public void apply(Player player, GameInfoHolder gameInfoHolder) {
        Direction advanceDirection = movementCalculator.advanceDirection(gameInfoHolder, player);

        movementHelper.move(
                gameInfoHolder,
                player,
                advanceDirection,
                spaces >= playerDist(gameInfoHolder) ? spaces + 1 : spaces
        );

        if (advanceDirection != movementCalculator.advanceDirection(gameInfoHolder, player)) {
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