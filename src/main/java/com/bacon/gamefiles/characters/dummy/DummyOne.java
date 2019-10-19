package com.bacon.gamefiles.characters.dummy;

import com.bacon.gamefiles.cards.specific.dummy.dummyone.*;
import com.bacon.gamefiles.characters.Character;
import com.bacon.gamefiles.characters.UniqueAbility;
import com.bacon.gamefiles.gameobjects.cards.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.google.common.collect.ImmutableList.of;
import static java.util.Arrays.asList;
import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

@Component
@Scope(value = SCOPE_PROTOTYPE)
public class DummyOne extends Character {
    @Autowired
    private DummyOneUa dummyOneUa;
    @Autowired
    private DummyOneBase dummyOneBase;
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

    @Override
    public List<Card> bases() {
        return of(dummyOneBase.dummyOneBase());
    }

    @Override
    public List<Card> styles() {
        return asList(
                dummyOneStyleOne.dummyOneStyleOne(),
                dummyOneStyleTwo.dummyOneStyleTwo(),
                dummyOneStyleThree.dummyOneStyleThree(),
                dummyOneStyleFour.dummyOneStyleFour(),
                dummyOneStyleFive.dummyOneStyleFive()
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

    @Override
    public UniqueAbility ua() {
        return dummyOneUa;
    }

    @Override
    public Object[] additionalData() {
        return new Object[0];
    }

    @Override
    public void setData(Object[] data) {
    }
}
