package com.bacon.selectors.choices;

import com.bacon.gameobjects.cards.CardEffect;
import com.bacon.holders.GameInfoHolder;
import com.bacon.player.Player;
import com.bacon.selectors.Selector;

import java.util.List;

public interface ChoiceSelector extends Selector {
    int choose(GameInfoHolder holder, Player player, CardEffect effect, List<?> choices);
}
