package com.bacon.effects.specific.shekthur;

import com.bacon.gameobjects.cards.CardEffect;
import com.bacon.holders.GameInfoHolder;
import com.bacon.player.Player;

import java.util.List;

import static com.bacon.attacks.AttackPairBonus.of;
import static com.bacon.attacks.AttackPairBonusType.POWER;
import static com.bacon.utils.ChoiceUtils.NO_CHOICES;

public class CombinationOH implements CardEffect {
    @Override
    public List<?> choices(Player player, GameInfoHolder gameInfoHolder) {
        return NO_CHOICES;
    }

    @Override
    public void apply(Player player, GameInfoHolder gameInfoHolder, int choiceIndex) {
        int beatNum = gameInfoHolder.infoHelper.currentBeatNumber(gameInfoHolder);
        if (beatNum - 1 == 0) {
            return;
        }

        if (player.prevBeats.get(beatNum - 1).attackHit) {
            player.attachBonus(beatNum, of(POWER, 2));
        }
    }
}
