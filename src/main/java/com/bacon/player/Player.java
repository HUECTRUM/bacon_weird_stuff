package com.bacon.player;

import com.bacon.characters.Character;
import com.bacon.gameobjects.cards.Card;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import static java.util.Collections.EMPTY_LIST;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Player {
    private static int counter = 0; //TODO: A way to distinguish display names in mirror matches

    public String playerId;

    public Character character;

    public List<Card> discardOne;
    public List<Card> discardTwo;

    public static Player fromCharacter(Character character) {
        return new Player(
                character.displayName() + (counter++),
                character,
                EMPTY_LIST,
                EMPTY_LIST
        );
    }
}
