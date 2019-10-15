package com.bacon.gamefiles.websocket;

import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import javax.websocket.Session;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class WsSender {
    public Map<UUID, Session> activeSessions = new HashMap<>();

    @SneakyThrows
    public void sendMessage(String msg, UUID gameId) {
        if (activeSessions.containsKey(gameId)) {
            activeSessions.get(gameId).getBasicRemote().sendText(msg);
        }
    }
}
