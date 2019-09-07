package com.bacon.cards.common.bases;

import com.bacon.effects.movement.common.AdvanceDodge;
import com.bacon.gameobjects.cards.Card;
import com.bacon.gameobjects.cards.CardEffect;
import com.bacon.gameobjects.triggers.EffectTrigger;

import java.util.List;
import java.util.Map;

import static com.bacon.gameobjects.cards.CardTriggeredEffect.triggeredEffect;
import static com.bacon.gameobjects.enums.CardType.BASE;
import static com.bacon.gameobjects.triggers.EffectTrigger.SOB;
import static com.bacon.utils.CardInitUtils.effectsMap;
import static java.math.BigDecimal.valueOf;
import static java.util.Arrays.asList;

public class Dodge {
    private static Map<EffectTrigger, List<CardEffect>> DODGE_EFFECTS_MAP = effectsMap(
            triggeredEffect(SOB, new AdvanceDodge(asList(1, 2, 3, -1, -2, -3)))
    );

    public static final Card DODGE = Card
            .builder()
            .cardType(BASE)
            .name("Dodge")
            .minRange(null)
            .maxRange(null)
            .power(0)
            .priority(valueOf(3))
            .stunGuard(0)
            .soak(0)
            .cardEffects(DODGE_EFFECTS_MAP)
            .build();
}
