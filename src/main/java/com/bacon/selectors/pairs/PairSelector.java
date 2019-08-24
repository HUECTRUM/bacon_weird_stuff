package com.bacon.selectors.pairs;

import com.bacon.attacks.AttackPair;
import com.bacon.player.Player;

public interface PairSelector {
    AttackPair selectPair(Player player);
}
