package com.bacon.statemachine.resolvers;

import com.bacon.events.EventEmitter;
import com.bacon.holders.GameInfoHolder;
import com.bacon.ioc.selector.SelectorHolder;
import com.bacon.player.Player;
import com.bacon.selectors.ante.AnteSelector;
import com.bacon.statemachine.conditions.StateTransitionCondition;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.bacon.events.EventType.P1_ANTE;
import static com.bacon.events.EventType.P2_ANTE;
import static com.bacon.events.GameEvent.event;
import static com.bacon.statemachine.conditions.RegularTransitionConditions.EMPTY;
import static java.util.List.of;
import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

@Component
@Slf4j
@Scope(value = SCOPE_PROTOTYPE)
public class AnteResolver {
    @Autowired
    private EventEmitter emitter;

    public SelectorHolder<AnteSelector> selectors = new SelectorHolder<>();

    public StateTransitionCondition ante(GameInfoHolder holder, boolean active) {
        Player antePlayer = active
                ? holder.infoHelper.previousBeatActive(holder) : holder.infoHelper.previousBeatReactive(holder);

        List<?> choices = antePlayer.character.ua().anteSelections(holder);

        if (choices.size() == 0) {
            return EMPTY;
        }

        int choice = choices.size() > 1 ? selectors.get(antePlayer, holder).anteChoice(holder, antePlayer, choices) : 0;
        log.info("Ante for player {} choice ind {} from {}", antePlayer.playerId, choice, choices);

        antePlayer.character.ua().applySelection(holder, antePlayer, choice);

        emitter.emit(event(antePlayer == holder.playerOne ? P1_ANTE : P2_ANTE, of(choices.get(choice)), holder.gameId));
        return EMPTY;
    }
}
