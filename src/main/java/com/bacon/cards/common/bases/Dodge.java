package com.bacon.cards.common.bases;

import com.bacon.gameobjects.cards.Card;

import static com.bacon.gameobjects.enums.CardType.BASE;
import static com.bacon.utils.CardInitUtils.EMPTY_EFFECTS_MAP;
import static java.math.BigDecimal.valueOf;

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
            .soak(0)
            .cardEffects(EMPTY_EFFECTS_MAP)
            .build();
}
