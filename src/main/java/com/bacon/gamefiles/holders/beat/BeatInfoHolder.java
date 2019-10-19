package com.bacon.gamefiles.holders.beat;

import com.bacon.gamefiles.attacks.AttackPair;
import com.bacon.gamefiles.gameobjects.cards.Card;
import com.bacon.gamefiles.player.Player;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
public class BeatInfoHolder {
    public int beatNumber;

    public Player activePlayer;
    public Player reactivePlayer;

    //just for convenience: avoids comparing activePlayer to playerOne all the time
    public AttackPair activePlayerPair;
    public AttackPair reactivePlayerPair;

    //todo: move to Player?
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
