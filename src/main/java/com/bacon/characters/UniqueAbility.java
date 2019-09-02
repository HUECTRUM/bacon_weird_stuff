package com.bacon.characters;

import com.bacon.holders.GameInfoHolder;

import java.util.List;

public interface UniqueAbility {
    List<?> anteSelections(GameInfoHolder holder);
    void applySelection(GameInfoHolder holder, int index);
}
