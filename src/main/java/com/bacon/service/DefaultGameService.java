package com.bacon.service;

import com.bacon.holders.GameInfoHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class DefaultGameService implements GameService {
    @Autowired
    private ApplicationContext context;

    @Override
    public GameInfoHolder createGame() {
        return context.getBean(GameInfoHolder.class);
    }
}
