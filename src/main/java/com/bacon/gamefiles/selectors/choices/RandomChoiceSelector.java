package com.bacon.gamefiles.selectors.choices;

import com.bacon.gamefiles.gameobjects.cards.CardEffect;
import com.bacon.gamefiles.holders.GameInfoHolder;
import com.bacon.gamefiles.player.Player;
import com.bacon.gamefiles.random.Randomizer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class RandomChoiceSelector implements ChoiceSelector {
    @Autowired
    private Randomizer randomizer;

    @Override
    public int choose(GameInfoHolder holder, Player player, CardEffect effect, List<?> choices) {
        int randomized = randomizer.randomize(choices.size());
        log.info("Choice selected index {}", randomized);
        return randomized;
    }
}
