package com.bacon.gamefiles.selectors.pairs;

import com.bacon.gamefiles.attacks.AttackPair;
import com.bacon.gamefiles.gameobjects.cards.Card;
import com.bacon.gamefiles.holders.GameInfoHolder;
import com.bacon.gamefiles.player.Player;
import com.bacon.gamefiles.random.Randomizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.bacon.gamefiles.attacks.AttackPair.fromCards;
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
