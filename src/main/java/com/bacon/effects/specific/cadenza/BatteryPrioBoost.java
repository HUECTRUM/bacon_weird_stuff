package com.bacon.effects.specific.cadenza;

import com.bacon.gameobjects.cards.CardEffect;
import com.bacon.holders.GameInfoHolder;
import com.bacon.player.Player;

import java.util.List;

import static com.bacon.attacks.AttackPairBonus.of;
import static com.bacon.attacks.AttackPairBonusType.PRIORITY;
import static com.bacon.utils.ChoiceUtils.NO_CHOICES;
import static java.math.BigDecimal.valueOf;

public class BatteryPrioBoost implements CardEffect {
    @Override
    public String effectName() {
        return "Battery prio boost";
    }

    @Override
    public List<?> choices(Player player, GameInfoHolder gameInfoHolder) {
        return NO_CHOICES;
    }

    @Override
    public void apply(Player player, GameInfoHolder gameInfoHolder, int choiceIndex) {
        int beatNum = gameInfoHolder.infoHelper.currentBeatNumber(gameInfoHolder);
        player.attachBonus(beatNum + 1, of(PRIORITY, valueOf(4)));
    }
}
