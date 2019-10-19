package com.bacon.gamefiles.statemachine;

import com.bacon.gamefiles.events.EventEmitter;
import com.bacon.gamefiles.holders.GameInfoHolder;
import com.bacon.gamefiles.statemachine.conditions.StateTransitionCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static com.bacon.gamefiles.events.GameEvent.event;
import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

@Component
@Scope(value = SCOPE_PROTOTYPE)
public class GameStateHolder {
    @Autowired
    private EventEmitter emitter;

    public StateTransitionCondition transition(GameInfoHolder holder) {
        GameStates state = holder.state;
        if (state.eventType != null) {
            emitter.emit(event(state.eventType, null, holder.gameId));
        }
        return state.transitionInternal(holder);
    }
}
