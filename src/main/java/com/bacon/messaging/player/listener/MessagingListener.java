package com.bacon.messaging.player.listener;

import com.bacon.events.EventListener;
import com.bacon.events.GameEvent;
import com.bacon.websocket.WsSender;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

public class MessagingListener implements EventListener {
    private static final ObjectMapper MESSAGE_MAPPER = new ObjectMapper();

    @Override
    @SneakyThrows
    public void onEvent(GameEvent event) {
        WsSender.INSTANCE.sendMessage(MESSAGE_MAPPER.writeValueAsString(event));
    }
}
