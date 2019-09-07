package com.bacon.characters.dummy;

import com.bacon.characters.UniqueAbility;
import com.bacon.holders.GameInfoHolder;
import com.bacon.player.Player;

import java.util.List;

import static java.util.Collections.singletonList;

public class DummyOneUa implements UniqueAbility {
    @Override
    public List<?> anteSelections(GameInfoHolder holder) {
        return singletonList("");
    }

    @Override
    public void applySelection(GameInfoHolder holder, Player player, int index) {

    }

    @Override
    public String toString() {
        return "DummyOneUa{}";
    }
}
