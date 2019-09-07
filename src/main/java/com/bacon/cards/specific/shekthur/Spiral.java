package com.bacon.cards.specific.shekthur;

import com.bacon.effects.specific.shekthur.SpiralBA;
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

public class Spiral {
    private static Map<EffectTrigger, List<CardEffect>> SPIRAL_EFFECTS = effectsMap(
            triggeredEffect(BA, new SpiralBA())
    );

    public static final Card SPIRAL = Card
            .builder()
            .cardType(STYLE)
            .name("Spiral")
            .minRange(0)
            .maxRange(0)
            .power(0)
            .priority(valueOf(-1))
            .stunGuard(0)
            .soak(0)
            .cardEffects(SPIRAL_EFFECTS)
            .build();
}
