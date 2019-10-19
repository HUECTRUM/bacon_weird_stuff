package com.bacon.gamefiles.cards;

import com.bacon.gamefiles.cards.common.bases.*;
import com.bacon.gamefiles.cards.common.styles.SwitchCard;
import com.bacon.gamefiles.cards.specific.cadenza.*;
import com.bacon.gamefiles.cards.specific.dummy.dummyone.*;
import com.bacon.gamefiles.cards.specific.shekthur.*;
import com.bacon.gamefiles.gameobjects.cards.Card;
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
    private SwitchCard switchCard;
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
    public List<Card> cardDb() {
        return asList(
                burst.burst(), shot.shot(), strike.strike(), drive.drive(), grasp.grasp(), dodge.dodge(), switchCard.switchCard(),
                dummyOneStyleOne.dummyOneStyleOne(), dummyOneStyleTwo.dummyOneStyleTwo(), dummyOneStyleThree.dummyOneStyleThree(),
                dummyOneStyleFour.dummyOneStyleFour(), dummyOneStyleFive.dummyOneStyleFive(), dummyOneBase.dummyOneBase(),
                unleashed.unleashed(), reaver.reaver(), combination.combination(), jugular.jugular(), spiral.spiral(), brand.brand(),
                battery.battery(), clockwork.clockwork(), grapnel.grapnel(), hydraulic.hydraulic(), mechanical.mechanical(), press.press()
        );
    }
}
