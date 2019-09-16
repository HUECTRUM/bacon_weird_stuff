package com.bacon.messaging.player;

import com.bacon.events.EventEmitter;
import com.bacon.messaging.player.listener.MessagingListener;
import com.bacon.messaging.player.messageparser.ParsedState;
import com.bacon.messaging.player.selectors.MessagingSelectorContainer;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import static com.bacon.messaging.player.MessagingState.NORMAL;

@Slf4j
public class PlayerMessaging {
    public final MessagingSelectorContainer selectorContainer;
    private MessagingState state;
    private Object value;

    public BlockingQueue<String> messageQueue = new ArrayBlockingQueue<>(1000);

    @SneakyThrows
    public PlayerMessaging() {
        selectorContainer = new MessagingSelectorContainer(this);
        EventEmitter.INSTANCE.register(new MessagingListener());
        MessageHub.INSTANCE.register(this);
        threadQueueJob();
    }

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
    public synchronized  <T> T await(MessagingState newState) {
        state = newState;
        wait();
        return (T) value;
    }

    private synchronized void processMsg(String msg) {
        if (state == null || state == NORMAL) {
            return;
        }

        ParsedState parsed = state.messageParser.parse(msg);
        if (parsed.parsed) {
            value = parsed.value;
            notifyAll();
        }
    }
}
