package com.bacon.gamefiles.selectors.pairs;

import com.bacon.gamefiles.attacks.AttackPair;
import com.bacon.gamefiles.holders.GameInfoHolder;
import com.bacon.gamefiles.player.Player;
import com.bacon.gamefiles.selectors.Selector;

public interface PairSelector extends Selector {
    AttackPair selectPair(Player player, GameInfoHolder holder);
}
