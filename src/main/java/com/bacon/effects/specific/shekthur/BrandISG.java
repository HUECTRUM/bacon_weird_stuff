package com.bacon.effects.specific.shekthur;

import com.bacon.gameobjects.cards.CardEffect;
import com.bacon.holders.GameInfoHolder;
import com.bacon.player.Player;

import java.math.BigDecimal;
import java.util.List;

import static com.bacon.attacks.AttackPairBonus.of;
import static com.bacon.attacks.AttackPairBonusType.ISG;
import static com.bacon.utils.ChoiceUtils.NO_CHOICES;
import static java.math.BigDecimal.valueOf;

public class BrandISG implements CardEffect {
    @Override
    public List<?> choices(Player player, GameInfoHolder gameInfoHolder) {
        return NO_CHOICES;
    }

    @Override
    public void apply(Player player, GameInfoHolder gameInfoHolder, int choiceIndex) {
        int beatNum = gameInfoHolder.infoHelper.currentBeatNumber(gameInfoHolder);
        BigDecimal prio = player.beatHolder.currentBeatPair.totalPriority(player, beatNum);

        if (prio.compareTo(valueOf(6)) >= 0) {
            player.attachBonus(beatNum, of(ISG, 0));
        }
    }
}