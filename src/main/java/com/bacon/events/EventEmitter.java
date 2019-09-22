package com.bacon.events;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public enum EventEmitter {
    INSTANCE;

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
