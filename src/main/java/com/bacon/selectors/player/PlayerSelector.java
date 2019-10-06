package com.bacon.selectors.player;

import com.bacon.holders.GameInfoHolder;
import com.bacon.player.Player;
import com.bacon.selectors.Selector;

public interface PlayerSelector extends Selector {
    Player selectPlayer(GameInfoHolder holder);
}
