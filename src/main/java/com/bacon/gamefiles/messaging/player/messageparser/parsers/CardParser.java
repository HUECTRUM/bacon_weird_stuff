package com.bacon.gamefiles.messaging.player.messageparser.parsers;

import com.bacon.gamefiles.cards.CardDatabase;
import com.bacon.gamefiles.gameobjects.cards.Card;
import com.bacon.gamefiles.messaging.player.messageparser.MessageParser;
import com.bacon.gamefiles.messaging.player.messageparser.ParsedState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.bacon.gamefiles.messaging.player.messageparser.ParsedState.parsed;

@Component
public class CardParser implements MessageParser<Card> {
    @Autowired
    private CardDatabase cardDatabase;

    @Override
    public ParsedState<Card> parse(String msg) {
        return cardDatabase.cardDb()
                .stream()
                .filter(card -> card.name.toLowerCase().equals(msg.toLowerCase()))
                .findAny()
                .map(card -> parsed(card, true))
                .orElse(parsed(null, false));
    }
}
