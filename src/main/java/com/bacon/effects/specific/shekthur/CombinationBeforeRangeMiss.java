package com.bacon.effects.specific.shekthur;

import com.bacon.gameobjects.cards.CardEffect;
import com.bacon.holders.GameInfoHolder;
import com.bacon.player.Player;

import java.util.List;

import static com.bacon.utils.ChoiceUtils.NO_CHOICES;
import static com.bacon.utils.FieldUtils.playerDist;

public class CombinationBeforeRangeMiss implements CardEffect {
    @Override
    public List<?> choices(Player player, GameInfoHolder gameInfoHolder) {
        return NO_CHOICES;
    }

    @Override
    public void apply(Player player, GameInfoHolder gameInfoHolder, int choiceIndex) {
        if (playerDist(gameInfoHolder) >= 3) {
            player.beatHolder.attackHit = false;
        }
    }
}
