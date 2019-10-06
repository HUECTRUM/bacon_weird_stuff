package com.bacon.statemachine;

import com.bacon.events.EventEmitter;
import com.bacon.holders.GameInfoHolder;
import com.bacon.statemachine.conditions.StateTransitionCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static com.bacon.events.GameEvent.event;
import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

@Component
@Scope(value = SCOPE_PROTOTYPE)
public class GameStateHolder {
    @Autowired
    private EventEmitter emitter;

    public GameStates state = GameStates.START;

    public StateTransitionCondition transition(GameInfoHolder holder) {
        if (state.eventType != null) {
            emitter.emit(event(state.eventType, null));
        }
        return state.transitionInternal(holder);
    }
}
