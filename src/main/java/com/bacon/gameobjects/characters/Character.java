package com.bacon.gameobjects.characters;

import com.bacon.gameobjects.cards.Card;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Character {
    public List<Card> stylesKit;
    public List<Card> basesKit;
}
