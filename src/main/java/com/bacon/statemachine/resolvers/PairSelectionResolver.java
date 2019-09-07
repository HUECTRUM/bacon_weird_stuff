package com.bacon.statemachine.resolvers;

import com.bacon.holders.GameInfoHolder;
import com.bacon.holders.beat.BeatInfoHolder;
import com.bacon.player.PlayerBeatHolder;
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
        //TODO: beat internal start state
        gameInfoHolder.beatInfoHolder = new BeatInfoHolder();
        gameInfoHolder.playerOne.beatHolder = new PlayerBeatHolder();
        gameInfoHolder.playerTwo.beatHolder = new PlayerBeatHolder();

        gameInfoHolder.beatInfoHolder.beatNumber = gameInfoHolder.infoHelper.lastBeatNumber(gameInfoHolder) + 1;
        BeatInfoHolder beatInfoHolder = gameInfoHolder.beatInfoHolder;

        gameInfoHolder.playerOne.beatHolder.currentBeatPair = pairSelector.selectPair(gameInfoHolder.playerOne);
        gameInfoHolder.playerTwo.beatHolder.currentBeatPair = pairSelector.selectPair(gameInfoHolder.playerTwo);

        beatInfoHolder.cardsPlayed(gameInfoHolder.playerOne.beatHolder.currentBeatPair.cards, true);
        beatInfoHolder.cardsPlayed(gameInfoHolder.playerTwo.beatHolder.currentBeatPair.cards, false);

        log.info("Pairs selected. Player one pair {} player two pair {}",
                mapList(gameInfoHolder.playerOne.beatHolder.currentBeatPair.cards, card -> card.name),
                mapList(gameInfoHolder.playerTwo.beatHolder.currentBeatPair.cards, card -> card.name)
        );
        return EMPTY;
    }
}
