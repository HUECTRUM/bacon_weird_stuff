package com.bacon.characters.specific.cadenza;

import com.bacon.characters.Character;
import com.bacon.characters.UniqueAbility;
import com.bacon.gameobjects.cards.Card;

import java.util.List;

import static com.bacon.cards.specific.cadenza.Battery.BATTERY;
import static com.bacon.cards.specific.cadenza.Clockwork.CLOCKWORK;
import static com.bacon.cards.specific.cadenza.Grapnel.GRAPNEL;
import static com.bacon.cards.specific.cadenza.Hydraulic.HYDRAULIC;
import static com.bacon.cards.specific.cadenza.Mechanical.MECHANICAL;
import static com.bacon.cards.specific.cadenza.Press.PRESS;
import static java.util.List.of;

public class Cadenza implements Character {
    private UniqueAbility ua = new CadenzaUa();

    @Override
    public List<Card> bases() {
        return of(PRESS);
    }

    @Override
    public List<Card> styles() {
        return of(BATTERY, CLOCKWORK, GRAPNEL, HYDRAULIC, MECHANICAL);
    }

    @Override
    public String name() {
        return "Cadenza";
    }

    @Override
    public String displayName() {
        return "C";
    }

    @Override
    public UniqueAbility ua() {
        return ua;
    }
}
