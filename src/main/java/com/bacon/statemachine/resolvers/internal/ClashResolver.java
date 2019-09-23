package com.bacon.statemachine.resolvers.internal;

import com.bacon.events.EventEmitter;
import com.bacon.gameobjects.cards.Card;
import com.bacon.holders.GameInfoHolder;
import com.bacon.holders.beat.BeatInfoHolder;
import com.bacon.ioc.selector.SelectorHolder;
import com.bacon.selectors.clash.ClashBaseSelector;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static com.bacon.attacks.AttackPair.fromCards;
import static com.bacon.events.EventType.*;
import static com.bacon.events.GameEvent.event;
import static com.bacon.gameobjects.enums.CardType.STYLE;
import static com.bacon.utils.StreamUtils.*;
import static java.util.Arrays.asList;
import static java.util.List.of;
import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

@Component
@Slf4j
@Scope(value = SCOPE_PROTOTYPE)
public class ClashResolver {
    public SelectorHolder<ClashBaseSelector> clashBaseSelectors = new SelectorHolder<>();

    public boolean resolveClash(GameInfoHolder holder) {
        EventEmitter.INSTANCE.emit(event(CLASH_BASE_SELECT, null));

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
        EventEmitter.INSTANCE.emit(event(P1_PAIR_REVEALED, of(holder.playerOne.beatHolder.currentBeatPair.cards)));
        EventEmitter.INSTANCE.emit(event(P2_PAIR_REVEALED, of(holder.playerTwo.beatHolder.currentBeatPair.cards)));

        return true;
    }
}
