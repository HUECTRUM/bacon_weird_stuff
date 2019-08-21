package com.bacon.statemachine.resolvers;

import com.bacon.holders.GameInfoHolder;
import com.bacon.selectors.player.PlayerSelector;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CharacterSelectionResolver {
    @Autowired
    private PlayerSelector playerSelector;

    public void selectPlayers(GameInfoHolder gameInfoHolder) {
        gameInfoHolder.playerOne = playerSelector.selectPlayer();
        gameInfoHolder.playerTwo = playerSelector.selectPlayer();

        gameInfoHolder.field.setPlayers(
                gameInfoHolder.playerOne.character.displayName(),
                gameInfoHolder.playerTwo.character.displayName()
        );

        log.info("Player select finished. Field {}", gameInfoHolder.field);
    }
}
