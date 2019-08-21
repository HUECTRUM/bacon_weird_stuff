package com.bacon.characters.dummy;

import com.bacon.characters.Character;
import com.bacon.gameobjects.cards.Card;

import java.util.List;

import static com.bacon.cards.specific.dummy.dummyone.DummyOneBase.DUMMY_ONE_BASE;
import static com.bacon.cards.specific.dummy.dummyone.DummyOneStyleFive.DUMMY_ONE_STYLE_FIVE;
import static com.bacon.cards.specific.dummy.dummyone.DummyOneStyleFour.DUMMY_ONE_STYLE_FOUR;
import static com.bacon.cards.specific.dummy.dummyone.DummyOneStyleOne.DUMMY_ONE_STYLE_ONE;
import static com.bacon.cards.specific.dummy.dummyone.DummyOneStyleThree.DUMMY_ONE_STYLE_THREE;
import static com.bacon.cards.specific.dummy.dummyone.DummyOneStyleTwo.DUMMY_ONE_STYLE_TWO;
import static com.google.common.collect.ImmutableList.of;
import static java.util.Arrays.asList;

public class DummyOne implements Character {
    @Override
    public List<Card> bases() {
        return of(DUMMY_ONE_BASE);
    }

    @Override
    public List<Card> styles() {
        return asList(
                DUMMY_ONE_STYLE_ONE,
                DUMMY_ONE_STYLE_TWO,
                DUMMY_ONE_STYLE_THREE,
                DUMMY_ONE_STYLE_FOUR,
                DUMMY_ONE_STYLE_FIVE
        );
    }

    @Override
    public String name() {
        return "Dummy1";
    }

    @Override
    public String displayName() {
        return "DM";
    }
}
