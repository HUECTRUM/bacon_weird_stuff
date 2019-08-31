package com.bacon.statemachine.resolvers;

import com.bacon.holders.GameInfoHolder;
import com.bacon.holders.beat.BeatInfoHolder;
import com.bacon.selectors.pairs.PairSelector;
import com.bacon.statemachine.conditions.StateTransitionCondition;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.bacon.statemachine.conditions.RegularTransitionConditions.EMPTY;
import static com.bacon.utils.StreamUtils.mapList;

@Component
@Slf4j
public class PairSelectionResolver {
    @Autowired
    private PairSelector pairSelector;

    public StateTransitionCondition selectPairs(GameInfoHolder gameInfoHolder) {
        gameInfoHolder.beatInfoHolder = new BeatInfoHolder();
        gameInfoHolder.beatInfoHolder.beatNumber = gameInfoHolder.infoHelper.lastBeatNumber(gameInfoHolder) + 1;
        BeatInfoHolder beatInfoHolder = gameInfoHolder.beatInfoHolder;

        beatInfoHolder.firstPlayerPair = pairSelector.selectPair(gameInfoHolder.playerOne);
        beatInfoHolder.secondPlayerPair = pairSelector.selectPair(gameInfoHolder.playerTwo);

        beatInfoHolder.cardsPlayed(beatInfoHolder.firstPlayerPair.cards, true);
        beatInfoHolder.cardsPlayed(beatInfoHolder.secondPlayerPair.cards, false);

        log.info("Pairs selected. Player one pair {} player two pair {}",
                mapList(beatInfoHolder.firstPlayerPair.cards, card -> card.name),
                mapList(beatInfoHolder.secondPlayerPair.cards, card -> card.name)
        );
        return EMPTY;
    }
}
