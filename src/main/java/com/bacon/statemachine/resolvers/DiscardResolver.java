package com.bacon.statemachine.resolvers;

import com.bacon.holders.GameInfoHolder;
import com.bacon.player.Player;
import com.bacon.selectors.discards.DiscardSelector;
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
public class DiscardResolver {
    public Map<Player, DiscardSelector> discardSelectors = new HashMap<>();

    public StateTransitionCondition selectDiscards(GameInfoHolder gameInfoHolder) {
        discardSelectors.get(gameInfoHolder.playerOne).selectDiscards(gameInfoHolder.playerOne);
        discardSelectors.get(gameInfoHolder.playerTwo).selectDiscards(gameInfoHolder.playerTwo);

        log.info("Discard state routine ended. Players one {} two {}",
                gameInfoHolder.playerOne, gameInfoHolder.playerTwo);
        return EMPTY;
    }
}
