package com.bacon.messaging.player.messageparser.parsers;

import com.bacon.gameobjects.cards.Card;
import com.bacon.messaging.player.messageparser.MessageParser;
import com.bacon.messaging.player.messageparser.ParsedState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.bacon.gameobjects.enums.CardType.BASE;
import static com.bacon.messaging.player.messageparser.ParsedState.parsed;

@Component
public class BaseParser implements MessageParser<Card> {
    @Autowired
    private CardParser cardParser;

    @Override
    public ParsedState<Card> parse(String msg) {
        ParsedState<Card> cardState = cardParser.parse(msg);
        return parsed(cardState.value, cardState.parsed && cardState.value.cardType == BASE);
    }
}
