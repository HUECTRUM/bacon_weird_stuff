package com.bacon.cards.specific.dummy.dummyone;

import com.bacon.gameobjects.cards.Card;

import static com.bacon.gameobjects.enums.CardType.STYLE;
import static com.bacon.utils.CardInitUtils.EMPTY_EFFECTS_MAP;
import static java.math.BigDecimal.valueOf;

public class DummyOneStyleFive {
    public static final Card DUMMY_ONE_STYLE_FIVE = Card
            .builder()
            .cardType(STYLE)
            .name("Dummy1Style5")
            .minRange(0)
            .maxRange(0)
            .power(0)
            .priority(valueOf(0))
            .stunGuard(4)
            .soak(0)
            .cardEffects(EMPTY_EFFECTS_MAP)
            .build();
}
