package com.bacon.cards.common.bases;

import com.bacon.effects.movement.common.Advance;
import com.bacon.gameobjects.cards.Card;
import com.bacon.gameobjects.cards.CardEffect;
import com.bacon.gameobjects.triggers.EffectTrigger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.bacon.gameobjects.enums.CardType.BASE;
import static com.bacon.gameobjects.triggers.EffectTrigger.*;
import static com.bacon.utils.CardInitUtils.EMPTY_EFFECTS_MAP;
import static java.math.BigDecimal.valueOf;
import static java.util.Collections.EMPTY_LIST;
import static java.util.Collections.singletonList;
import static java.util.Map.of;

public class Drive {
    private static Map<EffectTrigger, List<CardEffect>> DRIVE_EFFECTS_MAP = new HashMap<>(of(
            REVEAL, new ArrayList<CardEffect>(EMPTY_LIST),
            SOB, new ArrayList<CardEffect>(EMPTY_LIST),
            BA, new ArrayList<>(singletonList(new Advance(2))),
            OH, new ArrayList<CardEffect>(EMPTY_LIST),
            OD, new ArrayList<CardEffect>(EMPTY_LIST),
            AA, new ArrayList<CardEffect>(EMPTY_LIST),
            EOB, new ArrayList<CardEffect>(EMPTY_LIST)
    ));
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
            .soak(0)
            .cardEffects(EMPTY_EFFECTS_MAP)
            .build();
}
