package com.bacon.selectors.effectorder;

import com.bacon.gameobjects.cards.CardEffect;
import com.bacon.holders.GameInfoHolder;
import com.bacon.player.Player;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.range;

@Component
public class SequentialEffectOrderSelector implements EffectOrderSelector {
    @Override
    public List<Integer> effectOrder(Player player, GameInfoHolder gameInfoHolder, List<CardEffect> effects) {
        return range(0, effects.size()).boxed().collect(toList());
    }
}
