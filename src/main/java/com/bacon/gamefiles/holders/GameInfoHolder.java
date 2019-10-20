package com.bacon.gamefiles.holders;

import com.bacon.gamefiles.gameobjects.cards.CardEffect;
import com.bacon.gamefiles.gameobjects.field.Field;
import com.bacon.gamefiles.holders.beat.BeatInfoHolder;
import com.bacon.gamefiles.player.Player;
import com.bacon.gamefiles.statemachine.GameStateHolder;
import com.bacon.gamefiles.statemachine.GameStates;
import com.bacon.gamefiles.statemachine.conditions.StateTransitionCondition;
import com.bacon.gamefiles.statemachine.resolvers.ResolversContainer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.*;

import static com.bacon.gamefiles.statemachine.GameStates.GAME_END;
import static com.bacon.gamefiles.statemachine.GameStates.START;
import static com.bacon.gamefiles.utils.StreamUtils.mapList;
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
    @Autowired
    public Field field;

    public UUID gameId = new UUID(0L, 0L);

    public GameStates state = START;
    public boolean processingStopped = false;

    public Player playerOne;
    public Player playerTwo;

    public Map<BeatTriggerKey, List<CardEffect>> additionalEffects = new HashMap<>();

    public BeatInfoHolder beatInfoHolder;
    //init with a single one for beat 0
    public List<BeatInfoHolder> prevBeats = new ArrayList<>(singletonList(new BeatInfoHolder()));

    public void runActions() {
        runActionsUntil(singletonList(GAME_END));
        log.info("Game ended");
    }

    public void runActionsUntil(List<GameStates> endStates) {
        while (!endStates.contains(state) && !processingStopped) {
            log.info("State {}", state);
            StateTransitionCondition condition = stateHolder.transition(this);

            logGameInfo();
            state = state.nextStates().get(condition);
        }
        log.info("Terminal state {} met", state);
    }

    public void run() {
        //TODO: This causes a leak. Should be rewritten to stop when the ws connection is closed.
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
        log.info("Game id {}", gameId);
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

    public GameInfoHolder initGame(UUID gameId) {
        this.gameId = gameId;
        field.gameId = gameId;
        return this;
    }
}
