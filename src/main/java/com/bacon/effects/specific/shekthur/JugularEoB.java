package com.bacon.effects.specific.shekthur;

import com.bacon.characters.specific.shekthur.ShekthurUa;
import com.bacon.gameobjects.cards.CardEffect;
import com.bacon.holders.GameInfoHolder;
import com.bacon.player.Player;

import java.util.List;

import static com.bacon.utils.ChoiceUtils.NO_CHOICES;

public class JugularEoB implements CardEffect {
    @Override
    public List<?> choices(Player player, GameInfoHolder gameInfoHolder) {
        return NO_CHOICES;
    }

    @Override
    public void apply(Player player, GameInfoHolder gameInfoHolder, int choiceIndex) {
        ShekthurUa ua = (ShekthurUa) player.character.ua();
        ua.tokens = 3;
    }
}
