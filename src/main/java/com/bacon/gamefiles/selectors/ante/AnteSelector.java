package com.bacon.gamefiles.selectors.ante;

import com.bacon.gamefiles.holders.GameInfoHolder;
import com.bacon.gamefiles.player.Player;
import com.bacon.gamefiles.selectors.Selector;

import java.util.List;

public interface AnteSelector extends Selector {
    int anteChoice(GameInfoHolder holder, Player player, List<?> choices);
}
