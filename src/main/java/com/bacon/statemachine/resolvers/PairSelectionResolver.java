package com.bacon.statemachine.resolvers;

import com.bacon.holders.GameInfoHolder;
import com.bacon.holders.beat.BeatInfoHolder;
import com.bacon.ioc.selector.SelectorHolder;
import com.bacon.player.PlayerBeatHolder;
import com.bacon.selectors.pairs.PairSelector;
import com.bacon.statemachine.conditions.StateTransitionCondition;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static com.bacon.statemachine.conditions.RegularTransitionConditions.EMPTY;
import static com.bacon.utils.StreamUtils.mapList;
import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

@Component
@Slf4j
@Scope(value = SCOPE_PROTOTYPE)
public class PairSelectionResolver {
    public SelectorHolder<PairSelector> pairSelectors = new SelectorHolder<>();

    public StateTransitionCondition selectPairs(GameInfoHolder gameInfoHolder) {
        //TODO: beat internal start state
        gameInfoHolder.beatInfoHolder = new BeatInfoHolder();
        gameInfoHolder.playerOne.beatHolder = new PlayerBeatHolder();
        gameInfoHolder.playerTwo.beatHolder = new PlayerBeatHolder();

        gameInfoHolder.beatInfoHolder.beatNumber = gameInfoHolder.infoHelper.lastBeatNumber(gameInfoHolder) + 1;
        BeatInfoHolder beatInfoHolder = gameInfoHolder.beatInfoHolder;

        gameInfoHolder.playerOne.beatHolder.currentBeatPair =
                pairSelectors.get(gameInfoHolder.playerOne, gameInfoHolder).selectPair(gameInfoHolder.playerOne);
        gameInfoHolder.playerTwo.beatHolder.currentBeatPair =
                pairSelectors.get(gameInfoHolder.playerTwo, gameInfoHolder).selectPair(gameInfoHolder.playerTwo);

        beatInfoHolder.cardsPlayed(gameInfoHolder.playerOne.beatHolder.currentBeatPair.cards, true);
        beatInfoHolder.cardsPlayed(gameInfoHolder.playerTwo.beatHolder.currentBeatPair.cards, false);

        log.info("Pairs selected. Player one pair {} player two pair {}",
                mapList(gameInfoHolder.playerOne.beatHolder.currentBeatPair.cards, card -> card.name),
                mapList(gameInfoHolder.playerTwo.beatHolder.currentBeatPair.cards, card -> card.name)
        );
        return EMPTY;
    }
}
