package com.bacon.cards.common.bases;

import com.bacon.gameobjects.cards.Card;

import static com.bacon.gameobjects.enums.CardType.BASE;
import static java.math.BigDecimal.valueOf;
import static java.util.Collections.EMPTY_LIST;

public class Strike {
    public static final Card STRIKE = Card
            .builder()
            .cardType(BASE)
            .name("Strike")
            .minRange(1)
            .maxRange(1)
            .power(4)
            .priority(valueOf(3))
            .stunGuard(5)
            .soak(0)
            .effects(EMPTY_LIST)
            .build();
}
