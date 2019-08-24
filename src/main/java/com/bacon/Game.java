package com.bacon;

import com.bacon.holders.GameInfoHolder;
import com.bacon.statemachine.conditions.StateTransitionCondition;
import com.bacon.statemachine.states.GameState;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import static com.bacon.statemachine.GameStates.GAME_END;
import static com.bacon.statemachine.GameStates.START;

@Component
@Slf4j
public class Game {
    @Autowired
    public GameInfoHolder gameInfoHolder;

    public GameState state = START;

    @EventListener(ApplicationReadyEvent.class)
    public void startGame() {
        int cnt = 0; //TODO: remove
        while (state != GAME_END && cnt != 100) {
            log.info("State {} cnt {}", state, cnt);
            StateTransitionCondition condition = state.transition(gameInfoHolder);

            gameInfoHolder.logGameInfo();
            state = state.nextStates().get(condition);
            ++cnt;
        }
        log.info("Game ended");
    }
}
