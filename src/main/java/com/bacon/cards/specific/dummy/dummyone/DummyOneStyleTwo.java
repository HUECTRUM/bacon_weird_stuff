package com.bacon.cards.specific.dummy.dummyone;

import com.bacon.gameobjects.cards.Card;

import static com.bacon.gameobjects.enums.CardType.STYLE;
import static com.bacon.utils.CardInitUtils.EMPTY_EFFECTS_MAP;
import static java.math.BigDecimal.valueOf;

public class DummyOneStyleTwo {
    public static final Card DUMMY_ONE_STYLE_TWO = Card
            .builder()
            .cardType(STYLE)
            .name("Dummy1Style2")
            .minRange(1)
            .maxRange(3)
            .power(0)
            .priority(valueOf(0))
            .stunGuard(0)
            .soak(0)
            .cardEffects(EMPTY_EFFECTS_MAP)
            .build();
}
