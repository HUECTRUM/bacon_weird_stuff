package com.bacon.gameobjects.cards;

import com.bacon.holders.GameInfoHolder;
import com.bacon.player.Player;

import java.util.List;

public interface CardEffect {
    List<?> choices(Player player, GameInfoHolder gameInfoHolder);
    void apply(Player player, GameInfoHolder gameInfoHolder, int choiceIndex);
}
