package com.bacon.messaging.player.state;

import com.bacon.messaging.player.messageparser.MessageParser;
import com.bacon.messaging.player.messageparser.parsers.BaseParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AwaitClashMessagingState implements MessagingState {
    @Autowired
    private BaseParser baseParser;

    @Override
    public MessageParser messageParser() {
        return baseParser;
    }
}
