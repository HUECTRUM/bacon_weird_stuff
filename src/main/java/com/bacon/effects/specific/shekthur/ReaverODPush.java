package com.bacon.effects.specific.shekthur;

import com.bacon.gameobjects.cards.CardEffect;
import com.bacon.holders.GameInfoHolder;
import com.bacon.player.Player;

import java.util.List;

import static com.bacon.utils.ChoiceUtils.NO_CHOICES;
import static com.bacon.utils.calculation.MovementCalculator.maxAvailablePush;
import static com.bacon.utils.calculation.MovementCalculator.pushDirection;
import static com.bacon.utils.helper.MovementHelper.move;
import static java.lang.Math.min;

public class ReaverODPush implements CardEffect {
    @Override
    public List<?> choices(Player player, GameInfoHolder gameInfoHolder) {
        return NO_CHOICES;
    }

    @Override
    public void apply(Player player, GameInfoHolder gameInfoHolder, int choiceIndex) {
        int spaces = min(player.damageDealt, maxAvailablePush(gameInfoHolder, player));
        Player movePlayer = gameInfoHolder.infoHelper.opponent(gameInfoHolder, player);

        move(gameInfoHolder, movePlayer, pushDirection(gameInfoHolder, player), spaces);
    }
}
