package com.bacon.selectors.clash;

import com.bacon.gameobjects.cards.Card;
import com.bacon.holders.GameInfoHolder;
import com.bacon.player.Player;

public interface ClashBaseSelector {
    Card selectBase(Player player, GameInfoHolder gameInfoHolder);
}
