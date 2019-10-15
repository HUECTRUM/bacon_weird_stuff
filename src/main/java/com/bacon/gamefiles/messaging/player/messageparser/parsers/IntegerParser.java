package com.bacon.gamefiles.messaging.player.messageparser.parsers;

import com.bacon.gamefiles.messaging.player.messageparser.MessageParser;
import com.bacon.gamefiles.messaging.player.messageparser.ParsedState;
import org.springframework.stereotype.Component;

import static com.bacon.gamefiles.messaging.player.messageparser.ParsedState.parsed;
import static java.lang.Integer.parseInt;

@Component
public class IntegerParser implements MessageParser<Integer> {
    @Override
    public ParsedState<Integer> parse(String msg) {
        try {
            return parsed(parseInt(msg), true);
        } catch (Exception e) {
            return parsed(0, false);
        }
    }
}
