package com.bacon.gamefiles.statemachine.resolvers.internal;

import com.bacon.gamefiles.events.EventEmitter;
import com.bacon.gamefiles.gameobjects.cards.Card;
import com.bacon.gamefiles.holders.GameInfoHolder;
import com.bacon.gamefiles.holders.beat.BeatInfoHolder;
import com.bacon.gamefiles.ioc.selector.SelectorHolder;
import com.bacon.gamefiles.selectors.clash.ClashBaseSelector;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static com.bacon.gamefiles.attacks.AttackPair.fromCards;
import static com.bacon.gamefiles.events.EventType.*;
import static com.bacon.gamefiles.events.GameEvent.event;
import static com.bacon.gamefiles.gameobjects.enums.CardType.STYLE;
import static com.bacon.gamefiles.utils.StreamUtils.*;
import static java.util.Arrays.asList;
import static java.util.List.of;
import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

@Component
@Slf4j
@Scope(value = SCOPE_PROTOTYPE)
public class ClashResolver {
    @Autowired
    private EventEmitter emitter;

    public SelectorHolder<ClashBaseSelector> clashBaseSelectors = new SelectorHolder<>();

    public boolean resolveClash(GameInfoHolder holder) {
        emitter.emit(event(CLASH_BASE_SELECT, null, holder.gameId));

        BeatInfoHolder beatInfoHolder = holder.beatInfoHolder;
        log.info("A clash has occurred. Pairs p1 {} p2 {}",
                mapList(holder.playerOne.beatHolder.currentBeatPair.cards, card -> card.name),
                mapList(holder.playerTwo.beatHolder.currentBeatPair.cards, card -> card.name)
        );

        Card firstPlayerBase = clashBaseSelectors.get(holder.playerOne, holder).selectBase(holder.playerOne, holder);
        Card secondPlayerBase = clashBaseSelectors.get(holder.playerTwo, holder).selectBase(holder.playerTwo, holder);

        if (firstPlayerBase == null || secondPlayerBase == null) {
            return false;
        }

        Card firstPlayerStyle = getFirst(filterList(holder.playerOne.beatHolder.currentBeatPair.cards, card -> card.cardType == STYLE));
        Card secondPlayerStyle = getFirst(filterList(holder.playerTwo.beatHolder.currentBeatPair.cards, card -> card.cardType == STYLE));

        holder.playerOne.beatHolder.currentBeatPair = fromCards(asList(firstPlayerStyle, firstPlayerBase));
        holder.playerTwo.beatHolder.currentBeatPair = fromCards(asList(secondPlayerStyle, secondPlayerBase));

        beatInfoHolder.cardsPlayed(of(firstPlayerBase), true);
        beatInfoHolder.cardsPlayed(of(secondPlayerBase), false);

        log.info("Bases changed after clash. Player one pair {} player two pair {}",
                mapList(holder.playerOne.beatHolder.currentBeatPair.cards, card -> card.name),
                mapList(holder.playerTwo.beatHolder.currentBeatPair.cards, card -> card.name)
        );
        emitter.emit(event(P1_PAIR_REVEALED, of(holder.playerOne.beatHolder.currentBeatPair.cards), holder.gameId));
        emitter.emit(event(P2_PAIR_REVEALED, of(holder.playerTwo.beatHolder.currentBeatPair.cards), holder.gameId));

        return true;
    }
}
