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

        gameInfoHolder.playerOne.discardOne = gameInfoHolder.beatInfoHolder.firstPlayerPair;
        gameInfoHolder.playerTwo.discardOne = gameInfoHolder.beatInfoHolder.secondPlayerPair;

        return EMPTY;
    }
}
