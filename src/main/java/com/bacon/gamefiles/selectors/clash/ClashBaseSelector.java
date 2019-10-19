package com.bacon.gamefiles.selectors.clash;

import com.bacon.gamefiles.gameobjects.cards.Card;
import com.bacon.gamefiles.holders.GameInfoHolder;
import com.bacon.gamefiles.player.Player;
import com.bacon.gamefiles.selectors.Selector;

public interface ClashBaseSelector extends Selector {
    Card selectBase(Player player, GameInfoHolder gameInfoHolder);
}
