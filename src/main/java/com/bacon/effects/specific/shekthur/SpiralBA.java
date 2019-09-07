package com.bacon.effects.specific.shekthur;

import com.bacon.gameobjects.cards.CardEffect;
import com.bacon.holders.GameInfoHolder;
import com.bacon.player.Player;

import java.util.List;

import static com.bacon.attacks.AttackPairBonus.of;
import static com.bacon.attacks.AttackPairBonusType.POWER;
import static com.bacon.utils.FieldUtils.playerDist;
import static com.bacon.utils.StreamUtils.filterList;
import static com.bacon.utils.calculation.MovementCalculator.advanceDirection;
import static com.bacon.utils.calculation.MovementCalculator.maxAvailableAdvance;
import static com.bacon.utils.helper.MovementHelper.move;
import static java.util.Arrays.asList;

public class SpiralBA implements CardEffect {
    private List<Integer> advanceValues = asList(0, 1, 2, 3);

    @Override
    public List<Integer> choices(Player player, GameInfoHolder gameInfoHolder) {
        int advanceMax = maxAvailableAdvance(gameInfoHolder, player);
        return filterList(advanceValues, value -> value <= advanceMax);
    }

    @Override
    public void apply(Player player, GameInfoHolder gameInfoHolder, int choiceIndex) {
        int beatNum = gameInfoHolder.infoHelper.currentBeatNumber(gameInfoHolder);
        int spaces = choices(player, gameInfoHolder).get(choiceIndex);

        if (spaces == 0) {
            return;
        }

        move(
                gameInfoHolder,
                player,
                advanceDirection(gameInfoHolder, player),
                spaces >= playerDist(gameInfoHolder) ? spaces + 1 : spaces
        );

        player.attachBonus(beatNum, of(POWER, -spaces));
    }
}
