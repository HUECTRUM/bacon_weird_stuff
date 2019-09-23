package com.bacon.cards.specific.cadenza;

import com.bacon.gameobjects.cards.Card;

import static com.bacon.gameobjects.enums.CardType.STYLE;
import static com.bacon.utils.CardInitUtils.EMPTY_EFFECTS_MAP;
import static java.math.BigDecimal.valueOf;

public class Clockwork {
    public static final Card CLOCKWORK = Card
            .builder()
            .cardType(STYLE)
            .name("Clockwork")
            .minRange(0)
            .maxRange(0)
            .power(3)
            .priority(valueOf(-3))
            .stunGuard(0)
            .soak(3)
            .cardEffects(EMPTY_EFFECTS_MAP)
            .build();
}
