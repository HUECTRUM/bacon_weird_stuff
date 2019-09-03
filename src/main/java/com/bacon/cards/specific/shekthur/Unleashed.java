package com.bacon.cards.specific.shekthur;

import com.bacon.effects.movement.common.Advance;
import com.bacon.effects.movement.common.Retreat;
import com.bacon.effects.specific.shekthur.UnleashedEob;
import com.bacon.gameobjects.cards.Card;
import com.bacon.gameobjects.cards.CardEffect;
import com.bacon.gameobjects.triggers.EffectTrigger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.bacon.gameobjects.enums.CardType.STYLE;
import static com.bacon.gameobjects.triggers.EffectTrigger.*;
import static java.math.BigDecimal.valueOf;
import static java.util.Arrays.asList;
import static java.util.Collections.EMPTY_LIST;
import static java.util.Collections.singletonList;
import static java.util.Map.of;

public class Unleashed {
    private static Map<EffectTrigger, List<CardEffect>> UNLEASHED_EFFECTS = new HashMap<>(of(
            REVEAL, new ArrayList<CardEffect>(EMPTY_LIST),
            SOB, new ArrayList<CardEffect>(EMPTY_LIST),
            BA, new ArrayList<>(singletonList(new Advance(asList(1, 2)))),
            OH, new ArrayList<CardEffect>(EMPTY_LIST),
            OD, new ArrayList<CardEffect>(EMPTY_LIST),
            AA, new ArrayList<>(singletonList(new Retreat(asList(1, 2)))),
            EOB, new ArrayList<>(singletonList(new UnleashedEob()))
    ));
    public static final Card UNLEASHED = Card
            .builder()
            .cardType(STYLE)
            .name("Unleashed")
            .minRange(0)
            .maxRange(1)
            .power(-1)
            .priority(valueOf(0))
            .stunGuard(0)
            .soak(0)
            .cardEffects(UNLEASHED_EFFECTS)
            .build();
}
