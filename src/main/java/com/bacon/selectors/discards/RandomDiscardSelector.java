package com.bacon.selectors.discards;

import com.bacon.gameobjects.cards.Card;
import com.bacon.player.Player;
import com.bacon.random.NumberPair;
import com.bacon.random.Randomizer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

import static java.util.Arrays.asList;

@Component
@Slf4j
public class RandomDiscardSelector implements DiscardSelector {
    private static final Random random = new Random();

    @Autowired
    private Randomizer randomizer;

    @Override
    public List<List<Card>> selectDiscards(Player player) {
        NumberPair basesDiscard = randomizer.randomizeTwoNumbers(player.character.basesKit().size());
        NumberPair stylesDiscard = randomizer.randomizeTwoNumbers(player.character.stylesKit().size());

        List<Card> discardOne = asList(
                player.character.basesKit().get(basesDiscard.first),
                player.character.stylesKit().get(stylesDiscard.first)
        );

        List<Card> discardTwo = asList(
                player.character.basesKit().get(basesDiscard.second),
                player.character.stylesKit().get(stylesDiscard.second)
        );

        return asList(discardOne, discardTwo);
    }
}
