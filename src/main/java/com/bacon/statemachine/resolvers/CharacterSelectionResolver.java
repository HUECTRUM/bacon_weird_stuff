package com.bacon.statemachine.resolvers;

import com.bacon.holders.GameInfoHolder;
import com.bacon.ioc.selector.SelectorHolder;
import com.bacon.selectors.player.PlayerSelector;
import com.bacon.statemachine.conditions.StateTransitionCondition;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static com.bacon.statemachine.conditions.RegularTransitionConditions.EMPTY;
import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

@Component
@Slf4j
@Scope(value = SCOPE_PROTOTYPE)
public class CharacterSelectionResolver {
    public SelectorHolder<PlayerSelector> playerSelectors = new SelectorHolder<>();

    //todo: extract interface for resolvers?
    public StateTransitionCondition selectPlayers(GameInfoHolder gameInfoHolder) {
        gameInfoHolder.playerOne = playerSelectors.getByOrder(1).selectPlayer();
        gameInfoHolder.playerTwo = playerSelectors.getByOrder(2).selectPlayer();

        gameInfoHolder.field.setPlayers(
                gameInfoHolder.playerOne.playerId,
                gameInfoHolder.playerTwo.playerId
        );

        log.info("Player select finished. Field {}", gameInfoHolder.field);
        return EMPTY;
    }
}
