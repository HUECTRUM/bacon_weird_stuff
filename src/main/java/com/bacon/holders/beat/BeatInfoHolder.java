package com.bacon.holders.beat;

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

    public List<Card> firstPlayerPair;
    public List<Card> secondPlayerPair;
}
