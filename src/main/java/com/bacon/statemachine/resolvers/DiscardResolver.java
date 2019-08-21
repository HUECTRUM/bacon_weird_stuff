package com.bacon.statemachine.resolvers;

import com.bacon.holders.GameInfoHolder;
import com.bacon.selectors.discards.DiscardSelector;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DiscardResolver {
    @Autowired
    private DiscardSelector discardSelector;

    public void selectDiscards(GameInfoHolder gameInfoHolder) {
        discardSelector.selectDiscards(gameInfoHolder.playerOne);
        discardSelector.selectDiscards(gameInfoHolder.playerTwo);

        log.info("Discard state routine ended. Players one {} two {}",
                gameInfoHolder.playerOne, gameInfoHolder.playerTwo);
    }
}
