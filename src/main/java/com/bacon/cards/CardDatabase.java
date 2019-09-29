package com.bacon.cards;

import com.bacon.cards.common.bases.*;
import com.bacon.cards.common.styles.Switch;
import com.bacon.cards.specific.cadenza.*;
import com.bacon.cards.specific.dummy.dummyone.*;
import com.bacon.cards.specific.shekthur.*;
import com.bacon.gameobjects.cards.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.Arrays.asList;

@Component
public class CardDatabase {
    @Autowired
    private Burst burst;
    @Autowired
    private Shot shot;
    @Autowired
    private Strike strike;
    @Autowired
    private Drive drive;
    @Autowired
    private Grasp grasp;
    @Autowired
    private Dodge dodge;
    @Autowired
    private Switch switchCard;
    @Autowired
    private DummyOneStyleOne dummyOneStyleOne;
    @Autowired
    private DummyOneStyleTwo dummyOneStyleTwo;
    @Autowired
    private DummyOneStyleThree dummyOneStyleThree;
    @Autowired
    private DummyOneStyleFour dummyOneStyleFour;
    @Autowired
    private DummyOneStyleFive dummyOneStyleFive;
    @Autowired
    private DummyOneBase dummyOneBase;
    @Autowired
    private Unleashed unleashed;
    @Autowired
    private Reaver reaver;
    @Autowired
    private Combination combination;
    @Autowired
    private Jugular jugular;
    @Autowired
    private Spiral spiral;
    @Autowired
    private Brand brand;
    @Autowired
    private Battery battery;
    @Autowired
    private Clockwork clockwork;
    @Autowired
    private Grapnel grapnel;
    @Autowired
    private Hydraulic hydraulic;
    @Autowired
    private Mechanical mechanical;
    @Autowired
    private Press press;

    //todo: another way?
    public List<Card> CARD_DB = asList(
            burst.BURST, shot.SHOT, strike.STRIKE, drive.DRIVE, grasp.GRASP, dodge.DODGE, switchCard.SWITCH,
            dummyOneStyleOne.DUMMY_ONE_STYLE_ONE, dummyOneStyleTwo.DUMMY_ONE_STYLE_TWO, dummyOneStyleThree.DUMMY_ONE_STYLE_THREE,
            dummyOneStyleFour.DUMMY_ONE_STYLE_FOUR, dummyOneStyleFive.DUMMY_ONE_STYLE_FIVE, dummyOneBase.DUMMY_ONE_BASE,
            unleashed.UNLEASHED, reaver.REAVER, combination.COMBINATION, jugular.JUGULAR, spiral.SPIRAL, brand.BRAND,
            battery.BATTERY, clockwork.CLOCKWORK, grapnel.GRAPNEL, hydraulic.HYDRAULIC, mechanical.MECHANICAL, press.PRESS
    );
}
