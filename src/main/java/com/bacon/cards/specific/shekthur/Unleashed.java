package com.bacon.cards.specific.shekthur;

import com.bacon.effects.movement.common.Retreat;
import com.bacon.effects.specific.shekthur.UnleashedEob;
import com.bacon.gameobjects.cards.Card;
import com.bacon.gameobjects.cards.CardEffect;
import com.bacon.gameobjects.triggers.EffectTrigger;

import java.util.List;
import java.util.Map;

import static com.bacon.gameobjects.cards.CardTriggeredEffect.triggeredEffect;
import static com.bacon.gameobjects.enums.CardType.STYLE;
import static com.bacon.gameobjects.triggers.EffectTrigger.AA;
import static com.bacon.gameobjects.triggers.EffectTrigger.EOB;
import static com.bacon.utils.CardInitUtils.effectsMap;
import static java.math.BigDecimal.valueOf;
import static java.util.Arrays.asList;

public class Unleashed {
    private static Map<EffectTrigger, List<CardEffect>> UNLEASHED_EFFECTS = effectsMap(
            triggeredEffect(AA, new Retreat(asList(1, 2))),
            triggeredEffect(EOB, new UnleashedEob())
    );

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
