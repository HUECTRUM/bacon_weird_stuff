package com.bacon.cards.specific.cadenza;

import com.bacon.effects.movement.common.MoveOpponent;
import com.bacon.gameobjects.cards.Card;
import com.bacon.gameobjects.cards.CardEffect;
import com.bacon.gameobjects.triggers.EffectTrigger;

import java.util.List;
import java.util.Map;

import static com.bacon.gameobjects.cards.CardTriggeredEffect.triggeredEffect;
import static com.bacon.gameobjects.enums.CardType.STYLE;
import static com.bacon.gameobjects.triggers.EffectTrigger.OH;
import static com.bacon.utils.CardInitUtils.effectsMap;
import static java.math.BigDecimal.valueOf;

public class Grapnel {
    private static Map<EffectTrigger, List<CardEffect>> GRAPNEL_EFFECTS = effectsMap(
            triggeredEffect(OH, new MoveOpponent(List.of(0, -1, -2, -3)))
    );

    public static final Card GRAPNEL = Card
            .builder()
            .cardType(STYLE)
            .name("Grapnel")
            .minRange(2)
            .maxRange(4)
            .power(0)
            .priority(valueOf(0))
            .stunGuard(0)
            .soak(0)
            .cardEffects(GRAPNEL_EFFECTS)
            .build();
}
