package com.bacon;

import com.bacon.ioc.MessagingPlayerMode;
import com.bacon.ioc.RandomPlayerMode;
import com.bacon.service.GameService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class GameTest {
   @Autowired
   private GameService gameService;
   @Autowired
   private RandomPlayerMode randomMode;
   @Autowired
   private MessagingPlayerMode messagingPlayerMode;

    @EventListener(ApplicationReadyEvent.class)
    public void startGame() {
        gameService.createGame(messagingPlayerMode, randomMode).run();
    }
}
