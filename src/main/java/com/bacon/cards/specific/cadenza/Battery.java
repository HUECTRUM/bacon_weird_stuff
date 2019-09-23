package com.bacon.cards.specific.cadenza;

import com.bacon.effects.specific.cadenza.BatteryPrioBoost;
import com.bacon.gameobjects.cards.Card;
import com.bacon.gameobjects.cards.CardEffect;
import com.bacon.gameobjects.triggers.EffectTrigger;

import java.util.List;
import java.util.Map;

import static com.bacon.gameobjects.cards.CardTriggeredEffect.triggeredEffect;
import static com.bacon.gameobjects.enums.CardType.STYLE;
import static com.bacon.gameobjects.triggers.EffectTrigger.EOB;
import static com.bacon.utils.CardInitUtils.effectsMap;
import static java.math.BigDecimal.valueOf;

public class Battery {
    private static Map<EffectTrigger, List<CardEffect>> BATTERY_EFFECTS = effectsMap(
            triggeredEffect(EOB, new BatteryPrioBoost())
    );

    public static final Card BATTERY = Card
            .builder()
            .cardType(STYLE)
            .name("Battery")
            .minRange(0)
            .maxRange(0)
            .power(1)
            .priority(valueOf(-1))
            .stunGuard(0)
            .soak(0)
            .cardEffects(BATTERY_EFFECTS)
            .build();
}
