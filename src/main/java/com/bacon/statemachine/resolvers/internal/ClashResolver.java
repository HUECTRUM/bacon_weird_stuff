package com.bacon.statemachine.resolvers.internal;

import com.bacon.gameobjects.cards.Card;
import com.bacon.holders.GameInfoHolder;
import com.bacon.holders.beat.BeatInfoHolder;
import com.bacon.selectors.clash.ClashBaseSelector;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.bacon.attacks.AttackPair.fromCards;
import static com.bacon.gameobjects.enums.CardType.STYLE;
import static com.bacon.utils.StreamUtils.*;
import static java.util.Arrays.asList;
import static java.util.List.of;

@Component
@Slf4j
public class ClashResolver {
    @Autowired
    private ClashBaseSelector clashBaseSelector;

    public boolean resolveClash(GameInfoHolder holder) {
        BeatInfoHolder beatInfoHolder = holder.beatInfoHolder;
        log.info("A clash has occurred. Pairs p1 {} p2 {}",
                mapList(holder.playerOne.currentBeatPair.cards, card -> card.name),
                mapList(holder.playerTwo.currentBeatPair.cards, card -> card.name)
        );

        Card firstPlayerBase = clashBaseSelector.selectBase(holder.playerOne, holder);
        Card secondPlayerBase = clashBaseSelector.selectBase(holder.playerTwo, holder);

        if (firstPlayerBase == null || secondPlayerBase == null) {
            return false;
        }

        Card firstPlayerStyle = getFirst(filterList(holder.playerOne.currentBeatPair.cards, card -> card.cardType == STYLE));
        Card secondPlayerStyle = getFirst(filterList(holder.playerTwo.currentBeatPair.cards, card -> card.cardType == STYLE));

        holder.playerOne.currentBeatPair = fromCards(asList(firstPlayerStyle, firstPlayerBase));
        holder.playerTwo.currentBeatPair = fromCards(asList(secondPlayerStyle, secondPlayerBase));

        beatInfoHolder.cardsPlayed(of(firstPlayerBase), true);
        beatInfoHolder.cardsPlayed(of(secondPlayerBase), false);

        log.info("Bases changed after clash. Player one pair {} player two pair {}",
                mapList(holder.playerOne.currentBeatPair.cards, card -> card.name),
                mapList(holder.playerTwo.currentBeatPair.cards, card -> card.name)
        );
        return true;
    }
}
