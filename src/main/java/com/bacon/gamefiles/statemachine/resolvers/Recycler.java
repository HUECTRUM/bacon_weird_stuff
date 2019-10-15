package com.bacon.gamefiles.statemachine.resolvers;

import com.bacon.gamefiles.events.EventEmitter;
import com.bacon.gamefiles.holders.GameInfoHolder;
import com.bacon.gamefiles.statemachine.conditions.StateTransitionCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static com.bacon.gamefiles.events.EventType.*;
import static com.bacon.gamefiles.events.GameEvent.event;
import static com.bacon.gamefiles.statemachine.conditions.RegularTransitionConditions.EMPTY;
import static java.util.List.of;
import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

@Component
@Scope(value = SCOPE_PROTOTYPE)
public class Recycler {
    @Autowired
    private EventEmitter emitter;

    public StateTransitionCondition recycle(GameInfoHolder holder) {
        holder.playerOne.discardTwo = holder.playerOne.discardOne;
        holder.playerTwo.discardTwo = holder.playerTwo.discardOne;

        holder.playerOne.discardOne = holder.playerOne.beatHolder.currentBeatPair.cards;
        holder.playerTwo.discardOne = holder.playerTwo.beatHolder.currentBeatPair.cards;

        emitter.emit(event(P1_D1_DISCARD_CHANGED, holder.playerOne.discardOne, holder.gameId));
        emitter.emit(event(P1_D2_DISCARD_CHANGED, holder.playerOne.discardTwo, holder.gameId));
        emitter.emit(event(P2_D1_DISCARD_CHANGED, holder.playerTwo.discardOne, holder.gameId));
        emitter.emit(event(P2_D2_DISCARD_CHANGED, holder.playerTwo.discardTwo, holder.gameId));

        emitter.emit(event(P1_PAIR_REVEALED, of(), holder.gameId));
        emitter.emit(event(P2_PAIR_REVEALED, of(), holder.gameId));

        //todo: a separate state for game "cleaning" itself up after a beat
        holder.prevBeats.add(holder.beatInfoHolder);
        holder.playerOne.prevBeats.add(holder.playerOne.beatHolder);
        holder.playerTwo.prevBeats.add(holder.playerTwo.beatHolder);
        holder.playerOne.beatHolder.damageDealt
                = holder.playerOne.beatHolder.damageTaken
                = holder.playerTwo.beatHolder.damageDealt
                = holder.playerTwo.beatHolder.damageTaken = 0;
        return EMPTY;
    }
}
