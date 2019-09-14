package com.bacon.selectors.clash;

import com.bacon.gameobjects.cards.Card;
import com.bacon.holders.GameInfoHolder;
import com.bacon.player.Player;
import com.bacon.selectors.Selector;

public interface ClashBaseSelector extends Selector {
    Card selectBase(Player player, GameInfoHolder gameInfoHolder);
}
