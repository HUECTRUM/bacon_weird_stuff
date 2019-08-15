package com.bacon.cards.common.bases;

import com.bacon.gameobjects.Card;

import static com.bacon.gameobjects.enums.CardType.BASE;
import static java.math.BigDecimal.valueOf;
import static java.util.Collections.EMPTY_LIST;

public class Dodge {
    //TODO: EFFECTS
    public static final Card DODGE = Card
            .builder()
            .cardType(BASE)
            .name("Dodge")
            .minRange(0)
            .maxRange(0)
            .power(0)
            .priority(valueOf(3))
            .stunGuard(0)
            .effects(EMPTY_LIST)
            .build();
}
