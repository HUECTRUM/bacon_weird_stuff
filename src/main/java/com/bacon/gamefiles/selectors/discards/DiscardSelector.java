package com.bacon.gamefiles.selectors.discards;

import com.bacon.gamefiles.gameobjects.cards.Card;
import com.bacon.gamefiles.holders.GameInfoHolder;
import com.bacon.gamefiles.player.Player;
import com.bacon.gamefiles.selectors.Selector;

import java.util.List;

public interface DiscardSelector extends Selector {
    List<List<Card>> selectDiscards(Player player, GameInfoHolder holder);
}
