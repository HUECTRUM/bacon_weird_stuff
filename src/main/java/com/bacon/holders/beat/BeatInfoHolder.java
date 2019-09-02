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
    public int beatNumber;

    public Player activePlayer;
    public Player reactivePlayer;

    //hit & damage conditions
    public boolean activePlayerHit = true;
    public boolean reactivePlayerHit = true;

    public boolean activePlayerStunned = false;
    public boolean reactivePlayerStunned = false;

    //just for convenience: avoids comparing activePlayer to playerOne all the time
    public AttackPair activePlayerPair;
    public AttackPair reactivePlayerPair;

    public ClashInfoHolder clashInfoHolder = new ClashInfoHolder();

    //todo: move to Player?
    public void cardsPlayed(List<Card> cards, boolean first) {
        if (first) {
            clashInfoHolder.firstPlayerBasesPlayed.addAll(cards);
        } else {
            clashInfoHolder.secondPlayerBasesPlayed.addAll(cards);
        }
    }
}
