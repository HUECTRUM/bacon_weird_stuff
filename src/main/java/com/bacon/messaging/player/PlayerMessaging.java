package com.bacon.messaging.player;

import com.bacon.events.EventEmitter;
import com.bacon.messaging.player.listener.MessagingListener;
import com.bacon.messaging.player.message.GameMessage;
import com.bacon.messaging.player.messageparser.ParsedState;
import com.bacon.messaging.player.state.MessagingState;
import com.bacon.messaging.player.state.NormalMessagingState;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

@Slf4j
@Component
public class PlayerMessaging {
    @Autowired
    private MessagingListener listener;
    @Autowired
    private NormalMessagingState normalMessagingState;
    @Autowired
    private EventEmitter eventEmitter;

    private Map<UUID, MessagingState> states = new HashMap<>();
    private Map<UUID, Object> values = new HashMap<>();
    private Map<UUID, Object> locks = new HashMap<>();

    public BlockingQueue<GameMessage> messageQueue = new ArrayBlockingQueue<>(1000);

    @SneakyThrows
    private void threadQueueJob() {
        new Thread(this::startQueueJob)
                .start();
    }

    @SneakyThrows
    private void startQueueJob() {
        while (true) {
            log.info("Waiting for a message");
            processMsg(messageQueue.take());
        }
    }

    @SneakyThrows
    public synchronized  <T> T await(MessagingState newState, UUID gameId) {
        states.put(gameId, newState);
        locks.get(gameId).wait();
        return (T) values.get(gameId);
    }

    private synchronized void processMsg(GameMessage msg) {
        UUID gameId = msg.gameId;
        if (gameId == null || states.get(gameId).equals(normalMessagingState)) {
            return;
        }

        ParsedState parsedState = states.get(gameId).messageParser().parse(msg.msg);
        if (parsedState.parsed) {
            values.put(gameId, parsedState.value);
            locks.get(gameId).notifyAll();
        }
    }

    //init methods for class and game
    @SneakyThrows
    @PostConstruct
    public void initMessaging() {
        eventEmitter.register(listener);
        threadQueueJob();
    }

    public void initGame(UUID gameId) {
        states.put(gameId, normalMessagingState);
        values.put(gameId, null);
        locks.put(gameId, new Object());
    }

    public void teardownGame(UUID gameId) {
        states.remove(gameId);
        values.remove(gameId);
        locks.remove(gameId);
    }
}
