package com.bacon.gamefiles.messaging.player.messageparser.parsers;

import com.bacon.gamefiles.attacks.AttackPair;
import com.bacon.gamefiles.gameobjects.cards.Card;
import com.bacon.gamefiles.messaging.player.messageparser.MessageParser;
import com.bacon.gamefiles.messaging.player.messageparser.ParsedState;
import com.bacon.gamefiles.utils.StreamUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.bacon.gamefiles.messaging.player.messageparser.ParsedState.parsed;
import static com.bacon.gamefiles.utils.StreamUtils.mapList;
import static java.util.Arrays.asList;

@Component
public class AttackPairParser implements MessageParser<AttackPair> {
    @Autowired
    private CardParser cardParser;

    @Override
    public ParsedState<AttackPair> parse(String msg) {
        List<ParsedState<Card>> cards = mapList(asList(msg.split("\\s+")), card -> cardParser.parse(card));

        return cards.stream().anyMatch(c -> !c.parsed)
                ? parsed(null, false)
                : parsed(AttackPair.fromCards(StreamUtils.mapList(cards, card -> card.value)), true);
    }
}
