package com.bacon.statemachine.resolvers;

import com.bacon.holders.GameInfoHolder;
import com.bacon.holders.beat.BeatInfoHolder;
import com.bacon.selectors.pairs.PairSelector;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.bacon.utils.StreamUtils.mapList;

@Component
@Slf4j
public class PairSelectionResolver {
    @Autowired
    private PairSelector pairSelector;

    public void selectPairs(GameInfoHolder gameInfoHolder) {
        gameInfoHolder.beatNumber++;
        gameInfoHolder.beatInfoHolder = new BeatInfoHolder();

        gameInfoHolder.beatInfoHolder.firstPlayerPair = pairSelector.selectPair(gameInfoHolder.playerOne);
        gameInfoHolder.beatInfoHolder.secondPlayerPair = pairSelector.selectPair(gameInfoHolder.playerTwo);

        log.info("Pairs selected. Player one pair {} player two pair {}",
                mapList(gameInfoHolder.beatInfoHolder.firstPlayerPair, card -> card.name),
                mapList(gameInfoHolder.beatInfoHolder.secondPlayerPair, card -> card.name));
    }
}
