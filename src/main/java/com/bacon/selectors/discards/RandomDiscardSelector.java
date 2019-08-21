package com.bacon.selectors.discards;

import com.bacon.player.Player;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Random;

import static java.lang.Integer.MAX_VALUE;
import static java.util.Arrays.asList;

@Component
@Slf4j
public class RandomDiscardSelector implements DiscardSelector {
    private static final Random random = new Random();

    @Override
    public void selectDiscards(Player player) {
        NumberPair basesDiscard = randomizeTwoNumbers(player.character.basesKit().size());
        NumberPair stylesDiscard = randomizeTwoNumbers(player.character.stylesKit().size());

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

    private NumberPair randomizeTwoNumbers(int size) {
        int first = (random.nextInt() & MAX_VALUE) % size;
        int second = first;

        while (second == first) {
            second = (random.nextInt() & MAX_VALUE) % size;
        }

        return new NumberPair(first, second);
    }

    @AllArgsConstructor
    private static class NumberPair {
        public int first;
        public int second;
    }
}
