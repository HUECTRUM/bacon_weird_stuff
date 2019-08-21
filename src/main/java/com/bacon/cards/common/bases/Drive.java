package com.bacon.cards.common.bases;

import com.bacon.gameobjects.cards.Card;

import static com.bacon.gameobjects.enums.CardType.BASE;
import static java.math.BigDecimal.valueOf;
import static java.util.Collections.EMPTY_LIST;

public class Drive {
    //TODO: EFFECTS
    public static final Card DRIVE = Card
            .builder()
            .cardType(BASE)
            .name("Drive")
            .minRange(1)
            .maxRange(1)
            .power(3)
            .priority(valueOf(4))
            .stunGuard(0)
            .effects(EMPTY_LIST)
            .build();
}
