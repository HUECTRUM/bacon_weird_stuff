package com.bacon.websocket;

import lombok.SneakyThrows;

import javax.websocket.Session;

public enum WsSender {
    INSTANCE;

    public Session activeSession;

    @SneakyThrows
    public void sendMessage(String msg) {
        if (activeSession != null) {
            activeSession.getBasicRemote().sendText(msg);
        }
    }
}
