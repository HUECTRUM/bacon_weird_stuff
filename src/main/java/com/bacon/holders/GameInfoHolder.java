package com.bacon.holders;

import com.bacon.gameobjects.cards.CardEffect;
import com.bacon.gameobjects.field.Field;
import com.bacon.holders.beat.BeatInfoHolder;
import com.bacon.player.Player;
import com.bacon.statemachine.GameStateHolder;
import com.bacon.statemachine.GameStates;
import com.bacon.statemachine.conditions.StateTransitionCondition;
import com.bacon.statemachine.resolvers.ResolversContainer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.*;

import static com.bacon.statemachine.GameStates.GAME_END;
import static com.bacon.statemachine.GameStates.START;
import static com.bacon.utils.StreamUtils.mapList;
import static java.util.Collections.singletonList;
import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

@Component
@Scope(value = SCOPE_PROTOTYPE)
@Slf4j
public class GameInfoHolder {
    @Autowired
    public ResolversContainer resolversContainer;
    @Autowired
    public GameInfoHelper infoHelper;
    @Autowired
    private GameStateHolder stateHolder;

    public UUID gameId = new UUID(0L, 0L);

    private GameStates state = START;

    public Player playerOne;
    public Player playerTwo;

    public Field field;

    public Map<BeatTriggerKey, List<CardEffect>> additionalEffects = new HashMap<>();

    public BeatInfoHolder beatInfoHolder;
    //init with a single one for beat 0
    public List<BeatInfoHolder> prevBeats = new ArrayList<>(singletonList(new BeatInfoHolder()));

    public void runActions() {
        while (state != GAME_END) {
            log.info("State {}", state);
            StateTransitionCondition condition = stateHolder.transition(this);

            logGameInfo();
            state = state.nextStates().get(condition);
        }
        log.info("Game ended");
    }

    public void run() {
        new Thread(this::runActions).start();
    }

    public void addEffect(BeatTriggerKey key, CardEffect effect) {
        additionalEffects.putIfAbsent(key, new ArrayList<>());
        additionalEffects.get(key).add(effect);
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
            log.info("First player pair {} ", playerOne.beatHolder.currentBeatPair);
            log.info("Second player pair {}", playerTwo.beatHolder.currentBeatPair);
        }
        log.info("Prev beat num {}", infoHelper.lastBeatNumber(this));
        log.info("----------------------------------");
    }

    private void logPlayer(Player player, String name) {
        log.info("Player {} id {} name {} health {} ua {}",
                name, player.playerId, player.character.displayName(), player.health, player.character.ua());
        log.info("Player {} d1 {} d2 {}",
                name, mapList(player.discardOne, card -> card.name), mapList(player.discardTwo, card -> card.name));
    }

    public void initGame(UUID gameId) {
        this.gameId = gameId;
        this.field = new Field(gameId);
    }
}
