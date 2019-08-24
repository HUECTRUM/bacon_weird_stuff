package com.bacon.gameobjects.cards;

import com.bacon.holders.GameInfoHolder;
import com.bacon.player.Player;

public interface CardEffect {
    void apply(Player player, GameInfoHolder gameInfoHolder);
}
