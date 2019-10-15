package com.bacon.gamefiles.messaging.player.listener;

import com.bacon.gamefiles.events.EventListener;
import com.bacon.gamefiles.events.GameEvent;
import com.bacon.gamefiles.websocket.WsSender;
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
        sender.sendMessage(MESSAGE_MAPPER.writeValueAsString(event), event.gameId);
    }
}
