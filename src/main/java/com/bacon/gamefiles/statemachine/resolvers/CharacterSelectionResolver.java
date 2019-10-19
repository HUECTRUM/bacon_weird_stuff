package com.bacon.gamefiles.statemachine.resolvers;

import com.bacon.gamefiles.events.EventEmitter;
import com.bacon.gamefiles.holders.GameInfoHolder;
import com.bacon.gamefiles.ioc.selector.SelectorHolder;
import com.bacon.gamefiles.selectors.player.PlayerSelector;
import com.bacon.gamefiles.statemachine.conditions.StateTransitionCondition;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static com.bacon.gamefiles.events.EventType.*;
import static com.bacon.gamefiles.events.GameEvent.event;
import static com.bacon.gamefiles.statemachine.conditions.RegularTransitionConditions.EMPTY;
import static java.util.List.of;
import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

@Component
@Slf4j
@Scope(value = SCOPE_PROTOTYPE)
public class CharacterSelectionResolver {
    @Autowired
    private EventEmitter emitter;

    public SelectorHolder<PlayerSelector> playerSelectors = new SelectorHolder<>();

    //todo: extract interface for resolvers?
    public StateTransitionCondition selectPlayers(GameInfoHolder holder) {
        holder.playerOne = playerSelectors.getByOrder(1).selectPlayer(holder);
        holder.playerTwo = playerSelectors.getByOrder(2).selectPlayer(holder);

        holder.field.setPlayers(
                holder.playerOne.playerId,
                holder.playerTwo.playerId
        );

        log.info("Player select finished. Field {}", holder.field);

        emitter.emit(event(P1_CHAR_SELECTED,
                of(holder.playerOne.playerId, holder.playerOne.character.name()),
                holder.gameId));
        emitter.emit(event(P2_CHAR_SELECTED,
                of(holder.playerTwo.playerId, holder.playerTwo.character.name()),
                holder.gameId));

        emitter.emit(event(
                P1_UA_CHANGE,
                of(holder.playerOne.character.ua().description()),
                holder.gameId
        ));
        emitter.emit(event(
                P2_UA_CHANGE,
                of(holder.playerTwo.character.ua().description()),
                holder.gameId
        ));

        return EMPTY;
    }
}
