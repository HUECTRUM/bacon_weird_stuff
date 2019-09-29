package com.bacon.messaging.player.messageparser.parsers;

import com.bacon.gameobjects.cards.Card;
import com.bacon.messaging.player.messageparser.MessageParser;
import com.bacon.messaging.player.messageparser.ParsedState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.bacon.messaging.player.messageparser.ParsedState.parsed;
import static com.bacon.utils.StreamUtils.mapList;
import static java.util.Arrays.asList;

@Component
public class DiscardParser implements MessageParser<List<List<Card>>> {
    @Autowired
    private CardParser cardParser;

    @Override
    public ParsedState<List<List<Card>>> parse(String msg) {
        List<ParsedState<Card>> cards = mapList(asList(msg.split("\\s+")), card -> cardParser.parse(card));

        return cards.size() != 4 || cards.stream().anyMatch(c -> !c.parsed)
                ? parsed(null, false)
                : parsed(asList(asList(cards.get(0).value, cards.get(1).value), asList(cards.get(2).value, cards.get(3).value)), true);
    }
}
