package com.bacon.gamefiles.service;

import com.bacon.gamefiles.holders.GameInfoHolder;
import com.bacon.gamefiles.ioc.MessagingPlayerMode;
import com.bacon.gamefiles.ioc.PlayerMode;
import com.bacon.gamefiles.ioc.RandomPlayerMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DefaultGameService implements GameService {
    @Autowired
    private ApplicationContext context;
    @Autowired
    private RandomPlayerMode randomMode;
    @Autowired
    private MessagingPlayerMode messagingPlayerMode;

    @Override
    public GameInfoHolder createGame(PlayerMode firstMode, PlayerMode secondMode) {
        GameInfoHolder holder = context.getBean(GameInfoHolder.class);
        firstMode.inject(holder, 1);
        secondMode.inject(holder, 2);
        return holder;
    }

    @Override
    public GameInfoHolder createPVEGame(UUID gameId) {
        return createGame(messagingPlayerMode, randomMode)
                .initGame(gameId);
    }
}
