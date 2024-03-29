package com.bacon.gamefiles.selectors.clash;

import com.bacon.gamefiles.gameobjects.cards.Card;
import com.bacon.gamefiles.holders.GameInfoHolder;
import com.bacon.gamefiles.holders.beat.ClashInfoHolder;
import com.bacon.gamefiles.player.Player;
import com.bacon.gamefiles.random.Randomizer;
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
        return availableBases.size() > 0 ? availableBases.get(randomizer.randomize(availableBases.size())) : null;
    }
}
