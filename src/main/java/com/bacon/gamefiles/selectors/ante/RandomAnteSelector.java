package com.bacon.gamefiles.selectors.ante;

import com.bacon.gamefiles.holders.GameInfoHolder;
import com.bacon.gamefiles.player.Player;
import com.bacon.gamefiles.random.Randomizer;
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
