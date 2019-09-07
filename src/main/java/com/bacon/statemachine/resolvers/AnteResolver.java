package com.bacon.statemachine.resolvers;

import com.bacon.holders.GameInfoHolder;
import com.bacon.player.Player;
import com.bacon.selectors.ante.AnteSelector;
import com.bacon.statemachine.conditions.StateTransitionCondition;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.bacon.statemachine.conditions.RegularTransitionConditions.EMPTY;

@Component
@Slf4j
public class AnteResolver {
    @Autowired
    private AnteSelector selector;

    public StateTransitionCondition ante(GameInfoHolder holder, boolean active) {
        Player antePlayer = active
                ? holder.infoHelper.previousBeatActive(holder) : holder.infoHelper.previousBeatReactive(holder);

        List<?> choices = antePlayer.character.ua().anteSelections(holder);

        if (choices.size() == 0) {
            return EMPTY;
        }

        int choice = choices.size() > 1 ? selector.anteChoice(holder, antePlayer, choices) : 0;
        log.info("Ante for player {} choice ind {} from {}", antePlayer.playerId, choice, choices);

        antePlayer.character.ua().applySelection(holder, antePlayer, choice);
        return EMPTY;
    }
}
