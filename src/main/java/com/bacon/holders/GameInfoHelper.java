package com.bacon.holders;

import com.bacon.player.Player;
import com.bacon.random.Randomizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GameInfoHelper {
    @Autowired
    private Randomizer randomizer;

    //todo: inject holder
    public Player opponent(GameInfoHolder holder, Player player) {
        return player.equals(holder.playerOne) ? holder.playerTwo : holder.playerOne;
    }

    public int lastBeatNumber(GameInfoHolder holder) {
        return holder.prevBeats.isEmpty()
                ? 0
                : holder.prevBeats.get(holder.prevBeats.size() - 1).beatNumber;
    }

    public int currentBeatNumber(GameInfoHolder holder) {
        return lastBeatNumber(holder) + 1;
    }

    public Player previousBeatActive(GameInfoHolder holder) {
        return holder.prevBeats.size() == 1
                ? (randomizer.coinFlip() ? holder.playerOne : holder.playerTwo)
                : holder.prevBeats.get(holder.prevBeats.size() - 1).activePlayer;
    }

    public Player previousBeatReactive(GameInfoHolder holder) {
        Player active = previousBeatActive(holder);
        return active.equals(holder.playerOne) ? holder.playerTwo : holder.playerOne;
    }
}
