package com.bacon.selectors.effectorder;

import com.bacon.gameobjects.cards.CardEffect;
import com.bacon.holders.GameInfoHolder;
import com.bacon.player.Player;
import com.bacon.selectors.Selector;

import java.util.List;

public interface EffectOrderSelector extends Selector {
    List<Integer> effectOrder(Player player, GameInfoHolder gameInfoHolder, List<CardEffect> effects);
}
