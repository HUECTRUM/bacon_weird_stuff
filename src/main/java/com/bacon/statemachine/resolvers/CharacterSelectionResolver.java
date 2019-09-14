package com.bacon.statemachine.resolvers;

import com.bacon.holders.GameInfoHolder;
import com.bacon.player.Player;
import com.bacon.selectors.player.PlayerSelector;
import com.bacon.statemachine.conditions.StateTransitionCondition;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static com.bacon.statemachine.conditions.RegularTransitionConditions.EMPTY;
import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

@Component
@Slf4j
@Scope(value = SCOPE_PROTOTYPE)
public class CharacterSelectionResolver {
    public Map<Player, PlayerSelector> playerSelectors = new HashMap<>();

    //todo: extract interface for resolvers?
    public StateTransitionCondition selectPlayers(GameInfoHolder gameInfoHolder) {
        gameInfoHolder.playerOne = playerSelectors.get(gameInfoHolder.playerOne).selectPlayer();
        gameInfoHolder.playerTwo = playerSelectors.get(gameInfoHolder.playerTwo).selectPlayer();

        gameInfoHolder.field.setPlayers(
                gameInfoHolder.playerOne.playerId,
                gameInfoHolder.playerTwo.playerId
        );

        log.info("Player select finished. Field {}", gameInfoHolder.field);
        return EMPTY;
    }
}
