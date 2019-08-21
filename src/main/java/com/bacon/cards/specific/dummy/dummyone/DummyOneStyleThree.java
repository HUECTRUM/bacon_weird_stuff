package com.bacon.cards.specific.dummy.dummyone;

import com.bacon.gameobjects.Card;

import static com.bacon.gameobjects.enums.CardType.STYLE;
import static java.math.BigDecimal.valueOf;
import static java.util.Collections.EMPTY_LIST;

public class DummyOneStyleThree {
    public static final Card DUMMY_ONE_STYLE_THREE = Card
            .builder()
            .cardType(STYLE)
            .name("Dummy1Style3")
            .minRange(3)
            .maxRange(3)
            .power(0)
            .priority(valueOf(0))
            .stunGuard(0)
            .effects(EMPTY_LIST)
            .build();
}
