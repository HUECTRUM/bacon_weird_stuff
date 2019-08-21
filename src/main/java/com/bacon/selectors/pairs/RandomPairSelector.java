package com.bacon.selectors.pairs;

import com.bacon.gameobjects.cards.Card;
import com.bacon.player.Player;
import com.bacon.random.Randomizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.Arrays.asList;

@Component
public class RandomPairSelector implements PairSelector {
    @Autowired
    private Randomizer randomizer;

    @Override
    public List<Card> selectPair(Player player) {
        List<Card> availableBases = player.availableBases();
        List<Card> availableStyles = player.availableStyles();

        int baseIndex = randomizer.randomize(availableBases.size());
        int styleIndex = randomizer.randomize(availableStyles.size());

        return asList(availableBases.get(baseIndex), availableStyles.get(styleIndex));
    }
}
