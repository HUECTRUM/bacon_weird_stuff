package com.bacon.holders;

import com.bacon.gameobjects.field.Field;
import com.bacon.holders.beat.BeatInfoHolder;
import com.bacon.player.Player;
import com.bacon.statemachine.conditions.StateTransitionCondition;
import com.bacon.statemachine.resolvers.ResolversContainer;
import com.bacon.statemachine.states.GameState;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static com.bacon.statemachine.GameStates.GAME_END;
import static com.bacon.statemachine.GameStates.START;
import static com.bacon.utils.StreamUtils.mapList;
import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

@Component
@Scope(value = SCOPE_PROTOTYPE)
@Slf4j
public class GameInfoHolder {
    @Autowired
    public ResolversContainer resolversContainer;

    public Player playerOne;
    public Player playerTwo;

    public Field field = new Field();

    public BeatInfoHolder beatInfoHolder;

    private GameState state = START;

    public List<BeatInfoHolder> prevBeats = new ArrayList<>();

    public void run() {
        while (state != GAME_END) {
            log.info("State {}", state);
            StateTransitionCondition condition = state.transition(this);

            logGameInfo();
            state = state.nextStates().get(condition);
        }
        log.info("Game ended");
    }

    public int lastBeatNumber() {
        return prevBeats.isEmpty() ? 0 : prevBeats.get(prevBeats.size() - 1).beatNumber;
    }

    //logging for states
    //TODO: views
    public void logGameInfo() {
        log.info("----------------------------------");
        if (playerOne != null && playerTwo != null) {
            logPlayer(playerOne, "one");
            logPlayer(playerTwo, "two");
            log.info("Field {}", field.spaces);
        } else {
            log.info("Players not yet set");
        }
        if (beatInfoHolder != null) {
            log.info("First player pair {} ", beatInfoHolder.firstPlayerPair);
            log.info("Second player pair {}", beatInfoHolder.secondPlayerPair);
        }
        log.info("Prev beat num {}", lastBeatNumber());
        log.info("----------------------------------");
    }

    private void logPlayer(Player player, String name) {
        log.info("Player {} id {} name {} health {}",
                name, player.playerId, player.character.displayName(), player.health);
        log.info("Player {} d1 {} d2 {}",
                name, mapList(player.discardOne, card -> card.name), mapList(player.discardTwo, card -> card.name));
    }
}
