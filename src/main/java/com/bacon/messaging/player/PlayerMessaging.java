package com.bacon.messaging.player;

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

    private BlockingQueue<String> messageQueue = new ArrayBlockingQueue<>(1000);

    @SneakyThrows
    public PlayerMessaging() {
        selectorContainer = new MessagingSelectorContainer(this);
        threadQueueJob();
    }

    @SneakyThrows
    private void threadQueueJob() {
        new Thread(this::startQueueJob)
                .start();
    }


    private void startQueueJob() {
        while (true) {
            log.info("Waiting for a message");
            try {
                processMsg(messageQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @SneakyThrows
    public <T> T await(MessagingState newState) {
        state = newState;
        value.wait();
        return (T) value;
    }

    private void processMsg(String msg) {
        if (state == null || state == NORMAL) {
            return;
        }
        value = state.messageParser.parse(msg);
        value.notifyAll();
    }
}
