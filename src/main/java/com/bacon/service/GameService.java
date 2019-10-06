package com.bacon.service;

import com.bacon.holders.GameInfoHolder;
import com.bacon.ioc.PlayerMode;

import java.util.UUID;

public interface GameService {
    GameInfoHolder createGame(PlayerMode firstMode, PlayerMode secondMode);
    GameInfoHolder createPVEGame(UUID gameId);
}
