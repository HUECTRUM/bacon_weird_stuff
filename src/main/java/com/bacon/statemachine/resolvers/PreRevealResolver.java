package com.bacon.statemachine.resolvers;

import com.bacon.events.EventEmitter;
import com.bacon.holders.GameInfoHolder;
import com.bacon.statemachine.conditions.StateTransitionCondition;
import org.springframework.stereotype.Component;

import static com.bacon.events.EventType.P1_PAIR_REVEALED;
import static com.bacon.events.EventType.P2_PAIR_REVEALED;
import static com.bacon.events.GameEvent.event;
import static com.bacon.statemachine.conditions.RegularTransitionConditions.EMPTY;
import static java.util.List.of;

@Component
public class PreRevealResolver {
    public StateTransitionCondition resolve(GameInfoHolder holder) {
        EventEmitter.INSTANCE.emit(event(P1_PAIR_REVEALED, of(holder.playerOne.beatHolder.currentBeatPair.cards)));
        EventEmitter.INSTANCE.emit(event(P2_PAIR_REVEALED, of(holder.playerTwo.beatHolder.currentBeatPair.cards)));
        return EMPTY;
    }
}
