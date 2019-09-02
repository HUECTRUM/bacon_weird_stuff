package com.bacon.selectors.ante;

import com.bacon.holders.GameInfoHolder;
import com.bacon.player.Player;

import java.util.List;

public interface AnteSelector {
    int anteChoice(GameInfoHolder holder, Player player, List<?> choices);
}
