package com.bacon.selectors.clash;

import com.bacon.gameobjects.cards.Card;
import com.bacon.holders.GameInfoHolder;
import com.bacon.holders.beat.ClashInfoHolder;
import com.bacon.player.Player;
import com.bacon.random.Randomizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RandomBaseSelector implements ClashBaseSelector {
    @Autowired
    private Randomizer randomizer;

    @Override
    public Card selectBase(Player player, GameInfoHolder gameInfoHolder) {
        ClashInfoHolder clashInfoHolder = gameInfoHolder.beatInfoHolder.clashInfoHolder;
        List<Card> playedCards = player == gameInfoHolder.playerOne
                ? clashInfoHolder.firstPlayerBasesPlayed : clashInfoHolder.secondPlayerBasesPlayed;

        List<Card> availableBases = player.availableBasesAfterClash(playedCards);
        return availableBases.get(randomizer.randomize(availableBases.size()));
    }
}
