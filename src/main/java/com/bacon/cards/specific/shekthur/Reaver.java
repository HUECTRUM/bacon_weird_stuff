package com.bacon.cards.specific.shekthur;

import com.bacon.effects.movement.common.Advance;
import com.bacon.effects.specific.shekthur.ReaverODPush;
import com.bacon.gameobjects.cards.Card;
import com.bacon.gameobjects.cards.CardEffect;
import com.bacon.gameobjects.triggers.EffectTrigger;

import java.util.List;
import java.util.Map;

import static com.bacon.gameobjects.cards.CardTriggeredEffect.triggeredEffect;
import static com.bacon.gameobjects.enums.CardType.STYLE;
import static com.bacon.gameobjects.triggers.EffectTrigger.EOB;
import static com.bacon.gameobjects.triggers.EffectTrigger.OD;
import static com.bacon.utils.CardInitUtils.effectsMap;
import static java.math.BigDecimal.valueOf;
import static java.util.Arrays.asList;

public class Reaver {
    private static Map<EffectTrigger, List<CardEffect>> REAVER_EFFECTS = effectsMap(
            triggeredEffect(OD, new ReaverODPush()),
            triggeredEffect(EOB, new Advance(asList(1, 2)))
    );

    public static final Card REAVER = Card
            .builder()
            .cardType(STYLE)
            .name("Reaver")
            .minRange(0)
            .maxRange(1)
            .power(0)
            .priority(valueOf(0))
            .stunGuard(0)
            .soak(0)
            .cardEffects(REAVER_EFFECTS)
            .build();
}
