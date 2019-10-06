package com.bacon.statemachine.resolvers;

import com.bacon.events.EventEmitter;
import com.bacon.holders.GameInfoHolder;
import com.bacon.holders.beat.BeatInfoHolder;
import com.bacon.ioc.selector.SelectorHolder;
import com.bacon.player.PlayerBeatHolder;
import com.bacon.selectors.pairs.PairSelector;
import com.bacon.statemachine.conditions.StateTransitionCondition;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static com.bacon.events.EventType.PAIRS_SELECTED;
import static com.bacon.events.GameEvent.event;
import static com.bacon.statemachine.conditions.RegularTransitionConditions.EMPTY;
import static com.bacon.utils.StreamUtils.mapList;
import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

@Component
@Slf4j
@Scope(value = SCOPE_PROTOTYPE)
public class PairSelectionResolver {
    @Autowired
    private EventEmitter emitter;

    public SelectorHolder<PairSelector> pairSelectors = new SelectorHolder<>();

    public StateTransitionCondition selectPairs(GameInfoHolder holder) {
        //TODO: beat internal start state
        holder.beatInfoHolder = new BeatInfoHolder();
        holder.playerOne.beatHolder = new PlayerBeatHolder();
        holder.playerTwo.beatHolder = new PlayerBeatHolder();

        holder.beatInfoHolder.beatNumber = holder.infoHelper.lastBeatNumber(holder) + 1;
        BeatInfoHolder beatInfoHolder = holder.beatInfoHolder;

        holder.playerOne.beatHolder.currentBeatPair =
                pairSelectors.get(holder.playerOne, holder).selectPair(holder.playerOne, holder);
        holder.playerTwo.beatHolder.currentBeatPair =
                pairSelectors.get(holder.playerTwo, holder).selectPair(holder.playerTwo, holder);

        beatInfoHolder.cardsPlayed(holder.playerOne.beatHolder.currentBeatPair.cards, true);
        beatInfoHolder.cardsPlayed(holder.playerTwo.beatHolder.currentBeatPair.cards, false);

        log.info("Pairs selected. Player one pair {} player two pair {}",
                mapList(holder.playerOne.beatHolder.currentBeatPair.cards, card -> card.name),
                mapList(holder.playerTwo.beatHolder.currentBeatPair.cards, card -> card.name)
        );

        emitter.emit(event(PAIRS_SELECTED, null, holder.gameId));
        return EMPTY;
    }
}
