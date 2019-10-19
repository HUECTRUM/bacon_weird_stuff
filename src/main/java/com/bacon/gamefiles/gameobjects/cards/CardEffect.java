package com.bacon.gamefiles.gameobjects.cards;

import com.bacon.gamefiles.holders.GameInfoHolder;
import com.bacon.gamefiles.player.Player;

import java.util.List;

public interface CardEffect {
    String effectName();
    List<?> choices(Player player, GameInfoHolder gameInfoHolder);
    void apply(Player player, GameInfoHolder gameInfoHolder, int choiceIndex);

    default Object[] constructorParams() {
        return new Object[0];
    }
}
