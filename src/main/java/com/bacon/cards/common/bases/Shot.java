package com.bacon.cards.common.bases;

import com.bacon.gameobjects.cards.Card;

import static com.bacon.gameobjects.enums.CardType.BASE;
import static com.bacon.utils.CardInitUtils.EMPTY_EFFECTS_MAP;
import static java.math.BigDecimal.valueOf;

public class Shot {
    public static final Card SHOT = Card
            .builder()
            .cardType(BASE)
            .name("Shot")
            .minRange(1)
            .maxRange(4)
            .power(3)
            .priority(valueOf(2))
            .stunGuard(2)
            .soak(0)
            .cardEffects(EMPTY_EFFECTS_MAP)
            .build();
}
