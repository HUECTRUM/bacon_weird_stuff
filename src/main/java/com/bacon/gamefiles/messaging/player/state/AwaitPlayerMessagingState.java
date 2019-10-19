package com.bacon.gamefiles.messaging.player.state;

import com.bacon.gamefiles.messaging.player.messageparser.MessageParser;
import com.bacon.gamefiles.messaging.player.messageparser.parsers.CharacterParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AwaitPlayerMessagingState implements MessagingState {
    @Autowired
    private CharacterParser characterParser;

    @Override
    public MessageParser messageParser() {
        return characterParser;
    }
}
