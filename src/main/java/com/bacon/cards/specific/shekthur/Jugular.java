package com.bacon.cards.specific.shekthur;

import com.bacon.effects.movement.common.MoveOpponent;
import com.bacon.effects.specific.shekthur.JugularEoB;
import com.bacon.gameobjects.cards.Card;
import com.bacon.gameobjects.cards.CardEffect;
import com.bacon.gameobjects.triggers.EffectTrigger;

import java.util.List;
import java.util.Map;

import static com.bacon.gameobjects.cards.CardTriggeredEffect.triggeredEffect;
import static com.bacon.gameobjects.enums.CardType.STYLE;
import static com.bacon.gameobjects.triggers.EffectTrigger.EOB;
import static com.bacon.gameobjects.triggers.EffectTrigger.OH;
import static com.bacon.utils.CardInitUtils.effectsMap;
import static java.math.BigDecimal.valueOf;
import static java.util.Arrays.asList;

public class Jugular {
    private static Map<EffectTrigger, List<CardEffect>> JUGULAR_EFFECTS = effectsMap(
            triggeredEffect(OH, new MoveOpponent(asList(-1, 1))),
            triggeredEffect(EOB, new JugularEoB())
    );

    public static final Card JUGULAR = Card
            .builder()
            .cardType(STYLE)
            .name("Jugular")
            .minRange(0)
            .maxRange(0)
            .power(1)
            .priority(valueOf(2))
            .stunGuard(0)
            .soak(0)
            .cardEffects(JUGULAR_EFFECTS)
            .build();
}
