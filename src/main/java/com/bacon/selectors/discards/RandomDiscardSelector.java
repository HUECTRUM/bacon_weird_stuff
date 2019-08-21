package com.bacon.selectors.discards;

import com.bacon.player.Player;
import com.bacon.random.NumberPair;
import com.bacon.random.Randomizer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;

import static java.util.Arrays.asList;

@Component
@Slf4j
public class RandomDiscardSelector implements DiscardSelector {
    private static final Random random = new Random();

    @Autowired
    private Randomizer randomizer;

    @Override
    public void selectDiscards(Player player) {
        NumberPair basesDiscard = randomizer.randomizeTwoNumbers(player.character.basesKit().size());
        NumberPair stylesDiscard = randomizer.randomizeTwoNumbers(player.character.stylesKit().size());

        player.discardOne.addAll(asList(
                player.character.basesKit().get(basesDiscard.first),
                player.character.stylesKit().get(stylesDiscard.first)
        ));

        player.discardTwo.addAll(asList(
                player.character.basesKit().get(basesDiscard.second),
                player.character.stylesKit().get(stylesDiscard.second)
        ));

        log.info("Discards for player {} set. D1 {} D2 {}",
                player.character.displayName(), player.discardOne, player.discardTwo);
    }
}
