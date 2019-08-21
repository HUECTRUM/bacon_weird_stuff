package com.bacon.statemachine.resolvers;

import com.bacon.holders.GameInfoHolder;
import org.springframework.stereotype.Component;

@Component
public class Recycler {
    public void recycle(GameInfoHolder gameInfoHolder) {
        gameInfoHolder.playerOne.discardTwo = gameInfoHolder.playerOne.discardOne;
        gameInfoHolder.playerTwo.discardTwo = gameInfoHolder.playerTwo.discardOne;

        gameInfoHolder.playerOne.discardOne = gameInfoHolder.beatInfoHolder.firstPlayerPair;
        gameInfoHolder.playerTwo.discardOne = gameInfoHolder.beatInfoHolder.secondPlayerPair;
    }
}
