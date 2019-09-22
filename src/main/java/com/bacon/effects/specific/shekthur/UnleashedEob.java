package com.bacon.effects.specific.shekthur;

import com.bacon.characters.specific.shekthur.ShekthurUa;
import com.bacon.gameobjects.cards.CardEffect;
import com.bacon.holders.GameInfoHolder;
import com.bacon.player.Player;

import java.util.List;

import static com.bacon.attacks.AttackPairBonus.of;
import static com.bacon.attacks.AttackPairBonusType.POWER;
import static com.bacon.utils.ChoiceUtils.NO_CHOICES;

public class UnleashedEob implements CardEffect {
    @Override
    public String effectName() {
        return "Unleashed EOB";
    }

    @Override
    public List<?> choices(Player player, GameInfoHolder gameInfoHolder) {
        return NO_CHOICES;
    }

    @Override
    public void apply(Player player, GameInfoHolder gameInfoHolder, int choiceIndex) {
        ShekthurUa ua = (ShekthurUa) player.character.ua();
        int beatNum = gameInfoHolder.infoHelper.currentBeatNumber(gameInfoHolder);

        ua.gainTokens(2);
        player.attachBonus(beatNum + 1, of(POWER, 1));
    }
}
