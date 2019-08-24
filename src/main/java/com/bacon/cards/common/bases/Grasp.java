package com.bacon.cards.common.bases;

import com.bacon.gameobjects.cards.Card;

import static com.bacon.gameobjects.enums.CardType.BASE;
import static java.math.BigDecimal.valueOf;
import static java.util.Collections.EMPTY_LIST;

public class Grasp {
    //TODO: EFFECTS
    public static final Card GRASP = Card
            .builder()
            .cardType(BASE)
            .name("Grasp")
            .minRange(1)
            .maxRange(1)
            .power(2)
            .priority(valueOf(5))
            .stunGuard(0)
            .soak(0)
            .effects(EMPTY_LIST)
            .build();
}
