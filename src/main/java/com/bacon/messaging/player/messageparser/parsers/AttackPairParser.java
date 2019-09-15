package com.bacon.messaging.player.messageparser.parsers;

import com.bacon.attacks.AttackPair;
import com.bacon.gameobjects.cards.Card;
import com.bacon.messaging.player.messageparser.MessageParser;
import com.bacon.messaging.player.messageparser.ParsedState;
import com.bacon.utils.StreamUtils;

import java.util.List;

import static com.bacon.messaging.player.messageparser.ParsedState.parsed;
import static com.bacon.utils.StreamUtils.mapList;
import static java.util.Arrays.asList;

public class AttackPairParser implements MessageParser<AttackPair> {
    private CardParser cardParser = new CardParser();

    @Override
    public ParsedState<AttackPair> parse(String msg) {
        List<ParsedState<Card>> cards = mapList(asList(msg.split("\\s+")), card -> cardParser.parse(card));

        return cards.stream().anyMatch(c -> !c.parsed)
                ? parsed(null, false)
                : parsed(AttackPair.fromCards(StreamUtils.mapList(cards, card -> card.value)), true);
    }
}
