package com.bacon.statemachine.resolvers;

import com.bacon.holders.GameInfoHolder;
import com.bacon.selectors.player.PlayerSelector;
import com.bacon.statemachine.conditions.StateTransitionCondition;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.bacon.statemachine.conditions.RegularTransitionConditions.EMPTY;

@Component
@Slf4j
public class CharacterSelectionResolver {
    @Autowired
    private PlayerSelector playerSelector;

    //todo: extract interface for resolvers?
    public StateTransitionCondition selectPlayers(GameInfoHolder gameInfoHolder) {
        gameInfoHolder.playerOne = playerSelector.selectPlayer();
        gameInfoHolder.playerTwo = playerSelector.selectPlayer();

        gameInfoHolder.field.setPlayers(
                gameInfoHolder.playerOne.playerId,
                gameInfoHolder.playerTwo.playerId
        );

        log.info("Player select finished. Field {}", gameInfoHolder.field);
        return EMPTY;
    }
}
