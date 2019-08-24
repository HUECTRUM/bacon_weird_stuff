package com.bacon.cards.common.styles;

import com.bacon.gameobjects.cards.Card;

import static com.bacon.gameobjects.enums.CardType.STYLE;
import static com.bacon.utils.CardInitUtils.EMPTY_EFFECTS_MAP;
import static java.math.BigDecimal.valueOf;

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
            .cardEffects(EMPTY_EFFECTS_MAP)
            .build();
}
