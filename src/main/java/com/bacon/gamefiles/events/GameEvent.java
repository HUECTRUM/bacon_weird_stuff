package com.bacon.gamefiles.events;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Data
public class GameEvent {
    public EventType type;
    public List<?> args;
    public UUID gameId;

    public static GameEvent event(EventType type, List<?> args, UUID gameId) {
        return new GameEvent(type, args, gameId);
    }
}
