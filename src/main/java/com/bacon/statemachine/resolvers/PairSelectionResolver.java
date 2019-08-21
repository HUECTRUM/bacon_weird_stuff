package com.bacon.statemachine.resolvers;

import com.bacon.holders.GameInfoHolder;
import com.bacon.holders.beat.BeatInfoHolder;
import com.bacon.selectors.pairs.PairSelector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PairSelectionResolver {
    @Autowired
    private PairSelector pairSelector;

    public void selectPairs(GameInfoHolder gameInfoHolder) {
        gameInfoHolder.beatNumber++;
        gameInfoHolder.beatInfoHolder = new BeatInfoHolder();

        gameInfoHolder.beatInfoHolder.firstPlayerPair = pairSelector.selectPair(gameInfoHolder.playerOne);
        gameInfoHolder.beatInfoHolder.secondPlayerPair = pairSelector.selectPair(gameInfoHolder.playerTwo);
    }
}
