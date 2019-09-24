package com.bacon.websocket;

import com.bacon.messaging.player.MessageHub;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value="/game")
@Service
@Slf4j
@Component
public class GameWs {
    @Autowired
    private WsSender sender;
    @Autowired
    private MessageHub messageHub;

    @OnOpen
    public void onOpen(Session session) {
        sender.activeSession = session;
        log.info("Session opened");
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("Received message {}", message);
        messageHub.message(message);
    }

    @OnClose
    public void onClose(Session session) {
        sender.activeSession = null;
        log.info("Session closed");
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        log.error("WS error", throwable);
    }
}
