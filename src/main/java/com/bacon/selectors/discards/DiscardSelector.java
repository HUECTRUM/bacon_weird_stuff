package com.bacon.selectors.discards;

import com.bacon.player.Player;
import com.bacon.selectors.Selector;

public interface DiscardSelector extends Selector {
    void selectDiscards(Player player);
}
