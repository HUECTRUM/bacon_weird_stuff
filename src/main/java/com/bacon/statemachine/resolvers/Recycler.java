package com.bacon.statemachine.resolvers;

import com.bacon.events.EventEmitter;
import com.bacon.holders.GameInfoHolder;
import com.bacon.statemachine.conditions.StateTransitionCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static com.bacon.events.EventType.*;
import static com.bacon.events.GameEvent.event;
import static com.bacon.statemachine.conditions.RegularTransitionConditions.EMPTY;
import static java.util.List.of;
import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

@Component
@Scope(value = SCOPE_PROTOTYPE)
public class Recycler {
    @Autowired
    private EventEmitter emitter;

    public StateTransitionCondition recycle(GameInfoHolder gameInfoHolder) {
        gameInfoHolder.playerOne.discardTwo = gameInfoHolder.playerOne.discardOne;
        gameInfoHolder.playerTwo.discardTwo = gameInfoHolder.playerTwo.discardOne;

        gameInfoHolder.playerOne.discardOne = gameInfoHolder.playerOne.beatHolder.currentBeatPair.cards;
        gameInfoHolder.playerTwo.discardOne = gameInfoHolder.playerTwo.beatHolder.currentBeatPair.cards;

        emitter.emit(event(P1_D1_DISCARD_CHANGED, gameInfoHolder.playerOne.discardOne));
        emitter.emit(event(P1_D2_DISCARD_CHANGED, gameInfoHolder.playerOne.discardTwo));
        emitter.emit(event(P2_D1_DISCARD_CHANGED, gameInfoHolder.playerTwo.discardOne));
        emitter.emit(event(P2_D2_DISCARD_CHANGED, gameInfoHolder.playerTwo.discardTwo));

        emitter.emit(event(P1_PAIR_REVEALED, of()));
        emitter.emit(event(P2_PAIR_REVEALED, of()));

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
