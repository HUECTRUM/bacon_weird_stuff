package com.bacon.holders.beat;

import com.bacon.attacks.AttackPair;
import com.bacon.gameobjects.cards.Card;
import com.bacon.player.Player;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@NoArgsConstructor
public class BeatInfoHolder {
    public Player activePlayer;
    public Player reactivePlayer;

    public AttackPair firstPlayerPair;
    public AttackPair secondPlayerPair;

    public boolean activePlayerHit = true;
    public boolean reactivePlayerHit = true;

    //just for convenience: avoids comparing activePlayer to playerOne all the time
    public AttackPair activePlayerPair;
    public AttackPair reactivePlayerPair;

    public ClashInfoHolder clashInfoHolder = new ClashInfoHolder();

    public void cardsPlayed(List<Card> cards, boolean first) {
        if (first) {
            clashInfoHolder.firstPlayerBasesPlayed.addAll(cards);
        } else {
            clashInfoHolder.secondPlayerBasesPlayed.addAll(cards);
        }
    }
}
