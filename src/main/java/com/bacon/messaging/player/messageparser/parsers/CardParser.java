package com.bacon.messaging.player.messageparser.parsers;

import com.bacon.gameobjects.cards.Card;
import com.bacon.messaging.player.messageparser.MessageParser;
import com.bacon.messaging.player.messageparser.ParsedState;

import static com.bacon.cards.CardDatabase.CARD_DB;
import static com.bacon.messaging.player.messageparser.ParsedState.parsed;

public class CardParser implements MessageParser<Card> {
    @Override
    public ParsedState<Card> parse(String msg) {
        return CARD_DB
                .stream()
                .filter(card -> card.name.toLowerCase().equals(msg.toLowerCase()))
                .findAny()
                .map(card -> parsed(card, true))
                .orElse(parsed(null, false));
    }
}
