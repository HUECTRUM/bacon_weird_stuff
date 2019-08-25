package com.bacon.holders;

import com.bacon.gameobjects.field.Field;
import com.bacon.holders.beat.BeatInfoHolder;
import com.bacon.player.Player;
import com.bacon.statemachine.resolvers.ResolversContainer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.bacon.utils.StreamUtils.mapList;

@Component
@Slf4j
public class GameInfoHolder {
    @Autowired
    public ResolversContainer resolversContainer;

    public Player playerOne;
    public Player playerTwo;

    public Field field = new Field();

    public int beatNumber = 0;
    public BeatInfoHolder beatInfoHolder;


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
        log.info("----------------------------------");
    }

    private void logPlayer(Player player, String name) {
        log.info("Player {} id {} name {} health {}",
                name, player.playerId, player.character.displayName(), player.health);
        log.info("Player {} d1 {} d2 {}",
                name, mapList(player.discardOne, card -> card.name), mapList(player.discardTwo, card -> card.name));
    }
}
