package com.bacon.selectors.choices;

import com.bacon.gameobjects.cards.CardEffect;
import com.bacon.holders.GameInfoHolder;
import com.bacon.player.Player;
import com.bacon.random.Randomizer;
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
