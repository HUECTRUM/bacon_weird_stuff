package com.bacon.gamefiles.service;

import com.bacon.gamefiles.holders.GameInfoHolder;
import com.bacon.gamefiles.ioc.PlayerMode;

import java.util.UUID;

public interface GameService {
    GameInfoHolder createGame(PlayerMode firstMode, PlayerMode secondMode);
    GameInfoHolder createPVEGame(UUID gameId);
}
