package com.bacon.messaging.player.state;

import com.bacon.messaging.player.messageparser.MessageParser;
import com.bacon.messaging.player.messageparser.parsers.IntegerListParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AwaitEffectOrderMessagingState implements MessagingState {
    @Autowired
    private IntegerListParser integerListParser;

    @Override
    public MessageParser messageParser() {
        return integerListParser;
    }
}
