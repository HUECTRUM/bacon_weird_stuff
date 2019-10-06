package com.bacon.messaging.player.state;

import com.bacon.messaging.player.messageparser.MessageParser;
import org.springframework.stereotype.Component;

@Component
public class NormalMessagingState implements MessagingState {
    @Override
    public MessageParser messageParser() {
        return null;
    }
}
