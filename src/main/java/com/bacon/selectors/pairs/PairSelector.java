package com.bacon.selectors.pairs;

import com.bacon.gameobjects.cards.Card;
import com.bacon.player.Player;

import java.util.List;

public interface PairSelector {
    List<Card> selectPair(Player player);
}
