package com.bacon.effects.movement.common;

import com.bacon.gameobjects.cards.CardEffect;
import com.bacon.holders.GameInfoHolder;
import com.bacon.player.Player;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.bacon.utils.FieldUtils.playerDist;
import static com.bacon.utils.StreamUtils.concatLists;
import static com.bacon.utils.StreamUtils.filterList;
import static com.bacon.utils.calculation.MovementCalculator.*;
import static com.bacon.utils.helper.MovementHelper.move;
import static java.lang.Math.abs;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class AdvanceDodge implements CardEffect {
    public List<Integer> choices;

    @Override
    public List<Integer> choices(Player player, GameInfoHolder gameInfoHolder) {
        int advanceMax = maxAvailableAdvance(gameInfoHolder, player);
        List<Integer> advances = filterList(choices, choice -> choice > 0 && abs(choice) < advanceMax);

        int retreatMax = maxAvailableRetreat(gameInfoHolder, player);
        List<Integer> retreats = filterList(choices, choice -> choice < 0 && abs(choice) < retreatMax);

        return concatLists(advances, retreats);
    }

    @Override
    public void apply(Player player, GameInfoHolder gameInfoHolder, int choiceIndex) {
        int moveValue = choices(player, gameInfoHolder).get(choiceIndex);
        int spaces = abs(moveValue);

        Direction advanceDirection = advanceDirection(gameInfoHolder, player);
        Direction direction = moveValue > 0
                ? advanceDirection : retreatDirection(gameInfoHolder, player);

        int totalSpaces = direction == advanceDirection && spaces >= playerDist(gameInfoHolder)
                ? spaces + 1 : spaces;

        move(gameInfoHolder, player, direction, totalSpaces);
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