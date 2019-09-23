package com.bacon.cards.specific.cadenza;

import com.bacon.effects.movement.common.Advance;
import com.bacon.gameobjects.cards.Card;
import com.bacon.gameobjects.cards.CardEffect;
import com.bacon.gameobjects.triggers.EffectTrigger;

import java.util.List;
import java.util.Map;

import static com.bacon.gameobjects.cards.CardTriggeredEffect.triggeredEffect;
import static com.bacon.gameobjects.enums.CardType.STYLE;
import static com.bacon.gameobjects.triggers.EffectTrigger.BA;
import static com.bacon.utils.CardInitUtils.effectsMap;
import static java.math.BigDecimal.valueOf;
import static java.util.List.of;

public class Hydraulic {
    private static Map<EffectTrigger, List<CardEffect>> HYDRAULIC_EFFECTS = effectsMap(
            triggeredEffect(BA, new Advance(of(1)))
    );

    public static final Card HYDRAULIC = Card
            .builder()
            .cardType(STYLE)
            .name("Hydraulic")
            .minRange(0)
            .maxRange(0)
            .power(2)
            .priority(valueOf(-1))
            .stunGuard(0)
            .soak(1)
            .cardEffects(HYDRAULIC_EFFECTS)
            .build();
}
