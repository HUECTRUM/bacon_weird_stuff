package com.bacon.selectors.pairs;

import com.bacon.attacks.AttackPair;
import com.bacon.holders.GameInfoHolder;
import com.bacon.player.Player;
import com.bacon.selectors.Selector;

public interface PairSelector extends Selector {
    AttackPair selectPair(Player player, GameInfoHolder holder);
}
