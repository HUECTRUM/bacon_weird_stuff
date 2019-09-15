package com.bacon.cards;

import com.bacon.gameobjects.cards.Card;

import java.util.List;

import static com.bacon.cards.common.bases.Burst.BURST;
import static com.bacon.cards.common.bases.Dodge.DODGE;
import static com.bacon.cards.common.bases.Drive.DRIVE;
import static com.bacon.cards.common.bases.Grasp.GRASP;
import static com.bacon.cards.common.bases.Shot.SHOT;
import static com.bacon.cards.common.bases.Strike.STRIKE;
import static com.bacon.cards.common.styles.Switch.SWITCH;
import static com.bacon.cards.specific.dummy.dummyone.DummyOneBase.DUMMY_ONE_BASE;
import static com.bacon.cards.specific.dummy.dummyone.DummyOneStyleFive.DUMMY_ONE_STYLE_FIVE;
import static com.bacon.cards.specific.dummy.dummyone.DummyOneStyleFour.DUMMY_ONE_STYLE_FOUR;
import static com.bacon.cards.specific.dummy.dummyone.DummyOneStyleOne.DUMMY_ONE_STYLE_ONE;
import static com.bacon.cards.specific.dummy.dummyone.DummyOneStyleThree.DUMMY_ONE_STYLE_THREE;
import static com.bacon.cards.specific.dummy.dummyone.DummyOneStyleTwo.DUMMY_ONE_STYLE_TWO;
import static com.bacon.cards.specific.shekthur.Brand.BRAND;
import static com.bacon.cards.specific.shekthur.Combination.COMBINATION;
import static com.bacon.cards.specific.shekthur.Jugular.JUGULAR;
import static com.bacon.cards.specific.shekthur.Reaver.REAVER;
import static com.bacon.cards.specific.shekthur.Spiral.SPIRAL;
import static com.bacon.cards.specific.shekthur.Unleashed.UNLEASHED;
import static java.util.Arrays.asList;

public class CardDatabase {
    //todo: another way?
    public static List<Card> CARD_DB = asList(
            BURST, SHOT, STRIKE, DRIVE, GRASP, DODGE, SWITCH,
            DUMMY_ONE_STYLE_ONE, DUMMY_ONE_STYLE_TWO, DUMMY_ONE_STYLE_THREE, DUMMY_ONE_STYLE_FOUR, DUMMY_ONE_STYLE_FIVE, DUMMY_ONE_BASE,
            UNLEASHED, REAVER, COMBINATION, JUGULAR, SPIRAL, BRAND
    );
}
