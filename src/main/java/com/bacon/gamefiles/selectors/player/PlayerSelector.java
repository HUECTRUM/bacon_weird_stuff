package com.bacon.gamefiles.selectors.player;

import com.bacon.gamefiles.holders.GameInfoHolder;
import com.bacon.gamefiles.player.Player;
import com.bacon.gamefiles.selectors.Selector;

public interface PlayerSelector extends Selector {
    Player selectPlayer(GameInfoHolder holder);
}
