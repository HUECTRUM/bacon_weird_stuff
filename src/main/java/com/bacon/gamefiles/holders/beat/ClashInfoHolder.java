package com.bacon.gamefiles.holders.beat;

import com.bacon.gamefiles.gameobjects.cards.Card;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class ClashInfoHolder {
    public List<Card> firstPlayerBasesPlayed = new ArrayList<>();
    public List<Card> secondPlayerBasesPlayed = new ArrayList<>();
}
