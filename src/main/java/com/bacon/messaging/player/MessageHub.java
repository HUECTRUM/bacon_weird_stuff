package com.bacon.messaging.player;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageHub {
    @Autowired
    private PlayerMessaging playerMessaging;

    @SneakyThrows
    public void message(String msg) {
        playerMessaging.messageQueue.put(msg);
    }
}
