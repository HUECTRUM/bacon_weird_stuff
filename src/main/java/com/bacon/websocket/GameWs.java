package com.bacon.websocket;

import com.bacon.messaging.player.MessageHub;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import static java.util.UUID.fromString;

@ServerEndpoint(value="/game/{id}")
@Service
@Slf4j
@Component
public class GameWs {
    @Autowired
    private WsSender sender;
    @Autowired
    private MessageHub messageHub;

    @OnOpen
    public void onOpen(Session session, @PathParam("id")String id) {
        sender.activeSessions.put(fromString(id), session);
        messageHub.initGame(fromString(id));
        log.info("Session opened for game id {}", id);
    }

    @OnMessage
    public void onMessage(String message, @PathParam("id")String id) {
        log.info("Received message {}", message);
        messageHub.message(message, fromString(id));
    }

    @OnClose
    public void onClose(Session session, @PathParam("id")String id) {
        sender.activeSessions.remove(fromString(id));
        messageHub.teardownGame(fromString(id));
        log.info("Session closed for id {}", id);
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        log.error("WS error on session {}", session, throwable);
    }
}
