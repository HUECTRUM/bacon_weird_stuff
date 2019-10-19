package com.bacon.gamefiles.messaging.player.state;

import com.bacon.gamefiles.messaging.player.messageparser.MessageParser;
import com.bacon.gamefiles.messaging.player.messageparser.parsers.IntegerParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AwaitChoiceMessagingState implements MessagingState {
    @Autowired
    private IntegerParser integerParser;

    @Override
    public MessageParser messageParser() {
        return integerParser;
    }
}
