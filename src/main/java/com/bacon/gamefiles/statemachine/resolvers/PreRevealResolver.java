package com.bacon.gamefiles.statemachine.resolvers;

import com.bacon.gamefiles.events.EventEmitter;
import com.bacon.gamefiles.holders.GameInfoHolder;
import com.bacon.gamefiles.statemachine.conditions.StateTransitionCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.bacon.gamefiles.events.EventType.P1_PAIR_REVEALED;
import static com.bacon.gamefiles.events.EventType.P2_PAIR_REVEALED;
import static com.bacon.gamefiles.events.GameEvent.event;
import static com.bacon.gamefiles.statemachine.conditions.RegularTransitionConditions.EMPTY;
import static java.util.List.of;

@Component
public class PreRevealResolver {
    @Autowired
    private EventEmitter emitter;

    public StateTransitionCondition resolve(GameInfoHolder holder) {
        emitter.emit(event(P1_PAIR_REVEALED, of(holder.playerOne.beatHolder.currentBeatPair.cards), holder.gameId));
        emitter.emit(event(P2_PAIR_REVEALED, of(holder.playerTwo.beatHolder.currentBeatPair.cards), holder.gameId));
        return EMPTY;
    }
}
