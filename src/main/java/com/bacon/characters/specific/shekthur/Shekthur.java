package com.bacon.characters.specific.shekthur;

import com.bacon.characters.Character;
import com.bacon.characters.UniqueAbility;
import com.bacon.gameobjects.cards.Card;

import java.util.List;

public class Shekthur implements Character {
    @Override
    public List<Card> bases() {
        return null;
    }

    @Override
    public List<Card> styles() {
        return null;
    }

    @Override
    public String name() {
        return "Shekthur";
    }

    @Override
    public String displayName() {
        return "Sh";
    }

    @Override
    public UniqueAbility ua() {
        return new ShekthurUa();
    }
}
