package com.bacon.selectors.choices;

import com.bacon.gameobjects.cards.CardEffect;
import com.bacon.holders.GameInfoHolder;
import com.bacon.player.Player;

public interface ChoiceSelector {
    int choose(GameInfoHolder holder, Player player, CardEffect effect);
}
