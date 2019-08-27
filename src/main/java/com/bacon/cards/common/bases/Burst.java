package com.bacon.cards.common.bases;

import com.bacon.effects.movement.common.Retreat;
import com.bacon.gameobjects.cards.Card;
import com.bacon.gameobjects.cards.CardEffect;
import com.bacon.gameobjects.triggers.EffectTrigger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.bacon.gameobjects.enums.CardType.BASE;
import static com.bacon.gameobjects.triggers.EffectTrigger.*;
import static java.math.BigDecimal.valueOf;
import static java.util.Collections.EMPTY_LIST;
import static java.util.Collections.singletonList;
import static java.util.Map.of;

public class Burst {
    private static Map<EffectTrigger, List<CardEffect>> BURST_EFFECTS_MAP = new HashMap<>(of(
            REVEAL, new ArrayList<CardEffect>(EMPTY_LIST),
            SOB, new ArrayList<>(singletonList(new Retreat(2))),
            BA, new ArrayList<CardEffect>(EMPTY_LIST),
            OH, new ArrayList<CardEffect>(EMPTY_LIST),
            OD, new ArrayList<CardEffect>(EMPTY_LIST),
            AA, new ArrayList<CardEffect>(EMPTY_LIST),
            EOB, new ArrayList<CardEffect>(EMPTY_LIST)
    ));

    public static final Card BURST = Card
            .builder()
            .cardType(BASE)
            .name("Burst")
            .minRange(2)
            .maxRange(3)
            .power(3)
            .priority(valueOf(1))
            .stunGuard(0)
            .soak(0)
            .cardEffects(BURST_EFFECTS_MAP)
            .build();
}
