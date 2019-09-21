package com.bacon.controllers;

import com.bacon.ioc.MessagingPlayerMode;
import com.bacon.ioc.RandomPlayerMode;
import com.bacon.service.GameService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@Slf4j
@RequestMapping(produces = "application/json")
public class GameController {
    @Autowired
    private GameService gameService;
    @Autowired
    private RandomPlayerMode randomMode;
    @Autowired
    private MessagingPlayerMode messagingPlayerMode;

    @RequestMapping(path = "/new", method = POST)
    @ResponseStatus(NO_CONTENT)
    public void createGame() {
        gameService.createGame(messagingPlayerMode, randomMode).run();
    }
}
