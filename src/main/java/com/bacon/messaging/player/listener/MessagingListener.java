package com.bacon.messaging.player.listener;

import com.bacon.events.EventListener;
import com.bacon.events.GameEvent;
import com.bacon.websocket.WsSender;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessagingListener implements EventListener {
    @Autowired
    private WsSender sender;

    private static final ObjectMapper MESSAGE_MAPPER = new ObjectMapper();

    @Override
    @SneakyThrows
    public void onEvent(GameEvent event) {
        sender.sendMessage(MESSAGE_MAPPER.writeValueAsString(event));
    }
}
