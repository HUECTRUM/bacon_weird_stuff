package com.bacon.player;

import com.bacon.gameobjects.Card;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

import static java.util.UUID.randomUUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Player {
    public UUID playerId = randomUUID();

    public Character character;

    public List<Card> discardOne;
    public List<Card> discardTwo;
}
