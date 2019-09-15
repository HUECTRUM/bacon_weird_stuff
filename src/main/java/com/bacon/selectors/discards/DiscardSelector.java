package com.bacon.selectors.discards;

import com.bacon.gameobjects.cards.Card;
import com.bacon.player.Player;
import com.bacon.selectors.Selector;

import java.util.List;

public interface DiscardSelector extends Selector {
    List<List<Card>> selectDiscards(Player player);
}
