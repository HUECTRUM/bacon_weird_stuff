package com.bacon.cards.specific.dummy.dummyone;

import com.bacon.gameobjects.cards.Card;

import static com.bacon.gameobjects.enums.CardType.STYLE;
import static java.math.BigDecimal.valueOf;
import static java.util.Collections.EMPTY_LIST;

public class DummyOneStyleFour {
    public static final Card DUMMY_ONE_STYLE_FOUR = Card
            .builder()
            .cardType(STYLE)
            .name("Dummy1Style4")
            .minRange(0)
            .maxRange(0)
            .power(0)
            .priority(valueOf(1))
            .stunGuard(0)
            .soak(0)
            .effects(EMPTY_LIST)
            .build();
}
