package com.bacon.service;

import com.bacon.holders.GameInfoHolder;
import com.bacon.ioc.PlayerMode;

public interface GameService {
    GameInfoHolder createGame(PlayerMode firstMode, PlayerMode secondMode);
}
