package com.bacon.messaging.player;

import com.bacon.messaging.player.selectors.MessagingSelectorContainer;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import static com.bacon.messaging.player.MessagingState.NORMAL;
import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

@Component
@Scope(value = SCOPE_PROTOTYPE)
@Slf4j
public class PlayerMessaging {
    public MessagingSelectorContainer selectorContainer;
    private MessagingState state = NORMAL;
    private Object value = null;

    private BlockingQueue<String> messageQueue = new ArrayBlockingQueue<>(1000);

    @SneakyThrows
    public <T> T await(MessagingState newState) {
        state = newState;
        value.wait();
        return (T) value;
    }

    @PostConstruct
    @SneakyThrows
    public void queueJob() {
        selectorContainer = new MessagingSelectorContainer(this);

        while (true) {
            log.info("Waiting for a message");
            processMsg(messageQueue.take());
        }
    }

    private void processMsg(String msg) {
        if (state == NORMAL) {
            return;
        }
        value = state.messageParser.parse(msg);
        value.notifyAll();
    }
}
