package com.bacon.cards.common.styles;

import com.bacon.gameobjects.cards.Card;

import static com.bacon.gameobjects.enums.CardType.STYLE;
import static java.math.BigDecimal.valueOf;
import static java.util.Collections.EMPTY_LIST;

public class Switch {
    public static final Card SWITCH = Card
            .builder()
            .cardType(STYLE)
            .name("Switch")
            .minRange(0)
            .maxRange(0)
            .power(0)
            .priority(valueOf(0))
            .stunGuard(0)
            .soak(0)
            .effects(EMPTY_LIST)
            .build();
}
