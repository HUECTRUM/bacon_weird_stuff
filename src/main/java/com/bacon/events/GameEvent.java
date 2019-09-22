package com.bacon.events;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class GameEvent {
    public EventType type;
    public List<?> args;

    public static GameEvent event(EventType type, List<?> args) {
        return new GameEvent(type, args);
    }
}