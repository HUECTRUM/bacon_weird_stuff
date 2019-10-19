package com.bacon.gamefiles.messaging.player.state;

import com.bacon.gamefiles.messaging.player.messageparser.MessageParser;
import com.bacon.gamefiles.messaging.player.messageparser.parsers.DiscardParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AwaitDiscardsMessagingState implements MessagingState {
    @Autowired
    private DiscardParser discardParser;

    @Override
    public MessageParser messageParser() {
        return discardParser;
    }
}
