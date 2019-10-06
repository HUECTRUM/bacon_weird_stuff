package com.bacon.messaging.player.state;

import com.bacon.messaging.player.messageparser.MessageParser;

public interface MessagingState {
    MessageParser messageParser();
}
