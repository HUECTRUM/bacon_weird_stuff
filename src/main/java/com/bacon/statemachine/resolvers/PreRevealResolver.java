package com.bacon.statemachine.resolvers;

import com.bacon.events.EventEmitter;
import com.bacon.holders.GameInfoHolder;
import com.bacon.statemachine.conditions.StateTransitionCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.bacon.events.EventType.P1_PAIR_REVEALED;
import static com.bacon.events.EventType.P2_PAIR_REVEALED;
import static com.bacon.events.GameEvent.event;
import static com.bacon.statemachine.conditions.RegularTransitionConditions.EMPTY;
import static java.util.List.of;

@Component
public class PreRevealResolver {
    @Autowired
    private EventEmitter emitter;

    public StateTransitionCondition resolve(GameInfoHolder holder) {
        emitter.emit(event(P1_PAIR_REVEALED, of(holder.playerOne.beatHolder.currentBeatPair.cards)));
        emitter.emit(event(P2_PAIR_REVEALED, of(holder.playerTwo.beatHolder.currentBeatPair.cards)));
        return EMPTY;
    }
}
