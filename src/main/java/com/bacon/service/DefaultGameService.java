package com.bacon.service;

import com.bacon.holders.GameInfoHolder;
import com.bacon.ioc.PlayerMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class DefaultGameService implements GameService {
    @Autowired
    private ApplicationContext context;

    @Override
    public GameInfoHolder createGame(PlayerMode firstMode, PlayerMode secondMode) {
        GameInfoHolder holder = context.getBean(GameInfoHolder.class);
        firstMode.inject(holder, 1);
        secondMode.inject(holder, 2);
        return holder;
    }
}
