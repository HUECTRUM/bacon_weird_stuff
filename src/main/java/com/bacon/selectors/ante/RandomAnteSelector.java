package com.bacon.selectors.ante;

import com.bacon.holders.GameInfoHolder;
import com.bacon.player.Player;
import com.bacon.random.Randomizer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class RandomAnteSelector implements AnteSelector {
    @Autowired
    private Randomizer randomizer;

    @Override
    public int anteChoice(GameInfoHolder holder, Player player, List<?> choices) {
        int randomized = randomizer.randomize(choices.size());
        log.info("Ante choice selected index {}", randomized);
        return randomized;
    }
}
