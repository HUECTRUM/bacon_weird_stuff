package com.bacon.gamefiles.messaging.player;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static com.bacon.gamefiles.messaging.player.message.GameMessage.wsMessage;

@Component
public class MessageHub {
    @Autowired
    private PlayerMessaging playerMessaging;

    @SneakyThrows
    public void message(String msg, UUID gameId) {
        playerMessaging.messageQueue.put(wsMessage(gameId, msg));
    }

    public void initGame(UUID gameId) {
        playerMessaging.initGame(gameId);
    }

    public void teardownGame(UUID gameId) {
        playerMessaging.teardownGame(gameId);
    }
}
