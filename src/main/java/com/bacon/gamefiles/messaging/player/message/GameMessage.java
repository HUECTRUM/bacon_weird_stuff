package com.bacon.gamefiles.messaging.player.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameMessage {
    public UUID gameId;
    public String msg;

    public static GameMessage wsMessage(UUID gameId, String msg) {
        return new GameMessage(gameId, msg);
    }
}
