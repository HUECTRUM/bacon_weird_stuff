package com.bacon.events;

import java.util.ArrayList;
import java.util.List;

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
        listeners.forEach(listener -> listener.onEvent(event));
    }
}
