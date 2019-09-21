package com.bacon.statemachine.resolvers;

import com.bacon.events.EventEmitter;
import com.bacon.holders.GameInfoHolder;
import com.bacon.statemachine.conditions.StateTransitionCondition;
import org.springframework.stereotype.Component;

import static com.bacon.events.EventType.*;
import static com.bacon.events.GameEvent.event;
import static com.bacon.statemachine.conditions.RegularTransitionConditions.EMPTY;

@Component
public class Recycler {
    public StateTransitionCondition recycle(GameInfoHolder gameInfoHolder) {
        gameInfoHolder.playerOne.discardTwo = gameInfoHolder.playerOne.discardOne;
        gameInfoHolder.playerTwo.discardTwo = gameInfoHolder.playerTwo.discardOne;

        gameInfoHolder.playerOne.discardOne = gameInfoHolder.playerOne.beatHolder.currentBeatPair.cards;
        gameInfoHolder.playerTwo.discardOne = gameInfoHolder.playerTwo.beatHolder.currentBeatPair.cards;

        EventEmitter.INSTANCE.emit(event(P1_D1_DISCARD_CHANGED, gameInfoHolder.playerOne.discardOne));
        EventEmitter.INSTANCE.emit(event(P1_D2_DISCARD_CHANGED, gameInfoHolder.playerOne.discardTwo));
        EventEmitter.INSTANCE.emit(event(P2_D1_DISCARD_CHANGED, gameInfoHolder.playerTwo.discardOne));
        EventEmitter.INSTANCE.emit(event(P2_D2_DISCARD_CHANGED, gameInfoHolder.playerTwo.discardTwo));

        //todo: a separate state for game "cleaning" itself up after a beat
        gameInfoHolder.prevBeats.add(gameInfoHolder.beatInfoHolder);
        gameInfoHolder.playerOne.prevBeats.add(gameInfoHolder.playerOne.beatHolder);
        gameInfoHolder.playerTwo.prevBeats.add(gameInfoHolder.playerTwo.beatHolder);
        gameInfoHolder.playerOne.beatHolder.damageDealt
                = gameInfoHolder.playerOne.beatHolder.damageTaken
                = gameInfoHolder.playerTwo.beatHolder.damageDealt
                = gameInfoHolder.playerTwo.beatHolder.damageTaken = 0;
        return EMPTY;
    }
}
