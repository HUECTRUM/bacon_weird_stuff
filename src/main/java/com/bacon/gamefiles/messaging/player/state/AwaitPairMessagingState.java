package com.bacon.gamefiles.messaging.player.state;

import com.bacon.gamefiles.messaging.player.messageparser.MessageParser;
import com.bacon.gamefiles.messaging.player.messageparser.parsers.AttackPairParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AwaitPairMessagingState implements MessagingState {
    @Autowired
    private AttackPairParser attackPairParser;

    @Override
    public MessageParser messageParser() {
        return attackPairParser;
    }
}
