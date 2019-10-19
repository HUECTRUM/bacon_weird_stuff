package com.bacon.gamefiles.events;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class EventEmitter {
    List<EventListener> listeners = new ArrayList<>();

    public void register(EventListener listener) {
        listeners.add(listener);
    }

    public void unregister(EventListener listener) {
        listeners.remove(listener);
    }

    public void emit(GameEvent event) {
        log.info("Emitting event {}", event);
        listeners.forEach(listener -> listener.onEvent(event));
    }
}
