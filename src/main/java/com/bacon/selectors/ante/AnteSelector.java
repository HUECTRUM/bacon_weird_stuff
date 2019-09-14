package com.bacon.selectors.ante;

import com.bacon.holders.GameInfoHolder;
import com.bacon.player.Player;
import com.bacon.selectors.Selector;

import java.util.List;

public interface AnteSelector extends Selector {
    int anteChoice(GameInfoHolder holder, Player player, List<?> choices);
}
