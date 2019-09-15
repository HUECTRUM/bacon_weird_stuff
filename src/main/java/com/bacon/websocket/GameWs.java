package com.bacon.websocket;

import com.bacon.messaging.player.MessageHub;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

@ServerEndpoint(value="/game")
@Service
@Slf4j
public class GameWs {
    @OnOpen
    public void onOpen(Session session) throws IOException {
        log.info("Session opened");
    }

    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        log.info("Received message {}", message);
        MessageHub.INSTANCE.message(message);
    }

    @OnClose
    public void onClose(Session session) throws IOException {
        log.info("Session closed");
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        log.error("WS error", throwable);
    }
}
