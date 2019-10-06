package com.bacon.websocket;

import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import javax.websocket.Session;

@Component
public class WsSender {
    public Session activeSession;

    @SneakyThrows
    public void sendMessage(String msg) {
        if (activeSession != null) {
            activeSession.getBasicRemote().sendText(msg);
        }
    }
}
