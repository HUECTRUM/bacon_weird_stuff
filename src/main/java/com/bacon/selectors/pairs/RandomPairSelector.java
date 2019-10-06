package com.bacon.selectors.pairs;

import com.bacon.attacks.AttackPair;
import com.bacon.gameobjects.cards.Card;
import com.bacon.holders.GameInfoHolder;
import com.bacon.player.Player;
import com.bacon.random.Randomizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.bacon.attacks.AttackPair.fromCards;
import static java.util.Arrays.asList;

@Component
public class RandomPairSelector implements PairSelector {
    @Autowired
    private Randomizer randomizer;

    @Override
    public AttackPair selectPair(Player player, GameInfoHolder holder) {
        List<Card> availableBases = player.availableBases();
        List<Card> availableStyles = player.availableStyles();

        int baseIndex = randomizer.randomize(availableBases.size());
        int styleIndex = randomizer.randomize(availableStyles.size());

        return fromCards(asList(availableBases.get(baseIndex), availableStyles.get(styleIndex)));
    }
}
