package com.bacon.aifiles.ais.midbeatoptimal.processing.keys;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class GameBeatKey {
    public UUID gameId;
    public int beatNumber;

    public static GameBeatKey gameBeatKey(UUID gameId, int beatNumber) {
        return new GameBeatKey(gameId, beatNumber);
    }
}
