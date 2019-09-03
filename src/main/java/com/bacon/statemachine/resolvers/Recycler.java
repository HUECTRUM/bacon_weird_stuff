package com.bacon.statemachine.resolvers;

import com.bacon.holders.GameInfoHolder;
import com.bacon.statemachine.conditions.StateTransitionCondition;
import org.springframework.stereotype.Component;

import static com.bacon.statemachine.conditions.RegularTransitionConditions.EMPTY;

@Component
public class Recycler {
    public StateTransitionCondition recycle(GameInfoHolder gameInfoHolder) {
        gameInfoHolder.playerOne.discardTwo = gameInfoHolder.playerOne.discardOne;
        gameInfoHolder.playerTwo.discardTwo = gameInfoHolder.playerTwo.discardOne;

        gameInfoHolder.playerOne.discardOne = gameInfoHolder.playerOne.currentBeatPair.cards;
        gameInfoHolder.playerTwo.discardOne = gameInfoHolder.playerTwo.currentBeatPair.cards;

        //todo: a separate state for game "cleaning" itself up after a beat?
        gameInfoHolder.prevBeats.add(gameInfoHolder.beatInfoHolder);
        gameInfoHolder.playerOne.damageDealt = gameInfoHolder.playerOne.damageTaken =
                gameInfoHolder.playerTwo.damageDealt = gameInfoHolder.playerTwo.damageTaken = 0;
        return EMPTY;
    }
}
