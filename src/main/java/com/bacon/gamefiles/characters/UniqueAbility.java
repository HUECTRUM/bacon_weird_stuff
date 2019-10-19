package com.bacon.gamefiles.characters;

import com.bacon.gamefiles.holders.GameInfoHolder;
import com.bacon.gamefiles.player.Player;

import java.util.List;

public interface UniqueAbility {
    String description();
    List<?> anteSelections(GameInfoHolder holder);
    void applySelection(GameInfoHolder holder, Player player, int index);
}
