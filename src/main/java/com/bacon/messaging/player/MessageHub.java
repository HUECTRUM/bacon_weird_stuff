package com.bacon.messaging.player;

import lombok.SneakyThrows;

//todo: ioc container-handled
public enum MessageHub {
    INSTANCE;

    private PlayerMessaging messaging;

    public void register(PlayerMessaging playerMessaging) {
        messaging = playerMessaging;
    }

    @SneakyThrows
    public void message(String msg) {
        messaging.messageQueue.put(msg);
    }
}
