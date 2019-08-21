package com.bacon.cards.common.bases;

import com.bacon.gameobjects.cards.Card;

import static com.bacon.gameobjects.enums.CardType.BASE;
import static java.math.BigDecimal.valueOf;
import static java.util.Collections.EMPTY_LIST;

public class Burst {
    //TODO: EFFECTS
    public static final Card BURST = Card
            .builder()
            .cardType(BASE)
            .name("Burst")
            .minRange(2)
            .maxRange(3)
            .power(3)
            .priority(valueOf(1))
            .stunGuard(0)
            .effects(EMPTY_LIST)
            .build();
}
