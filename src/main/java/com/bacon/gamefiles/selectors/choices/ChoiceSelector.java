package com.bacon.gamefiles.selectors.choices;

import com.bacon.gamefiles.gameobjects.cards.CardEffect;
import com.bacon.gamefiles.holders.GameInfoHolder;
import com.bacon.gamefiles.player.Player;
import com.bacon.gamefiles.selectors.Selector;

import java.util.List;

public interface ChoiceSelector extends Selector {
    int choose(GameInfoHolder holder, Player player, CardEffect effect, List<?> choices);
}
