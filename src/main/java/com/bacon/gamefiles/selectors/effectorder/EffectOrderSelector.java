package com.bacon.gamefiles.selectors.effectorder;

import com.bacon.gamefiles.gameobjects.cards.CardEffect;
import com.bacon.gamefiles.holders.GameInfoHolder;
import com.bacon.gamefiles.player.Player;
import com.bacon.gamefiles.selectors.Selector;

import java.util.List;

public interface EffectOrderSelector extends Selector {
    List<Integer> effectOrder(Player player, GameInfoHolder gameInfoHolder, List<CardEffect> effects);
}
