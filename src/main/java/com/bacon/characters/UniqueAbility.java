package com.bacon.characters;

import com.bacon.holders.GameInfoHolder;
import com.bacon.player.Player;

import java.util.List;

public interface UniqueAbility {
    List<?> anteSelections(GameInfoHolder holder);
    void applySelection(GameInfoHolder holder, Player player, int index);
}
