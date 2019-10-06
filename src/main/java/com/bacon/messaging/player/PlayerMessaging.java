package com.bacon.messaging.player;

import com.bacon.events.EventEmitter;
import com.bacon.messaging.player.listener.MessagingListener;
import com.bacon.messaging.player.messageparser.ParsedState;
import com.bacon.messaging.player.state.MessagingState;
import com.bacon.messaging.player.state.NormalMessagingState;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
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

    private MessagingState state;
    private ThreadLocal<Object> value = new ThreadLocal<>();
    private ThreadLocal<Object> lock = new ThreadLocal<>();

    public BlockingQueue<String> messageQueue = new ArrayBlockingQueue<>(1000);

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
        lock.wait();
        return (T) value;
    }

    private synchronized void processMsg(String msg) {
        if (state == null || state.equals(normalMessagingState)) {
            return;
        }

        ParsedState parsed = state.messageParser().parse(msg);
        if (parsed.parsed) {
            value.set(parsed.value);
            lock.notifyAll();
        }
    }

    @SneakyThrows
    @PostConstruct
    public void initMessaging() {
        eventEmitter.register(listener);
        threadQueueJob();
    }
}
