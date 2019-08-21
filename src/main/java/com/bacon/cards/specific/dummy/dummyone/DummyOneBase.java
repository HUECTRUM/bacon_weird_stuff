package com.bacon.cards.specific.dummy.dummyone;

import com.bacon.gameobjects.cards.Card;

import static com.bacon.gameobjects.enums.CardType.BASE;
import static java.math.BigDecimal.valueOf;
import static java.util.Collections.EMPTY_LIST;

public class DummyOneBase {
    public static final Card DUMMY_ONE_BASE = Card
            .builder()
            .cardType(BASE)
            .name("Dummy1UB")
            .minRange(1)
            .maxRange(3)
            .power(3)
            .priority(valueOf(0))
            .stunGuard(0)
            .effects(EMPTY_LIST)
            .build();
}
